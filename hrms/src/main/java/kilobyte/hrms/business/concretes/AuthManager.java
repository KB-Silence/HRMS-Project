package kilobyte.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.AuthService;
import kilobyte.hrms.business.abstracts.EmployerService;
import kilobyte.hrms.business.abstracts.UnemployedService;
import kilobyte.hrms.business.abstracts.VerificationService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorDataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.EmployeeDao;
import kilobyte.hrms.dataAccess.abstracts.EmployerDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.dataAccess.abstracts.UserDao;
import kilobyte.hrms.entities.concretes.Employer;
import kilobyte.hrms.entities.concretes.Unemployed;
import kilobyte.hrms.entities.concretes.User;
import kilobyte.hrms.entities.dtos.LoginDto;
import kilobyte.hrms.entities.dtos.LoginReturnDto;

@Service
public class AuthManager implements AuthService {

	private UnemployedService unemployedService;
	private EmployerService employerService;
	private VerificationService verificationService;

	private UserDao userDao;
	private UnemployedDao unemployedDao;
	private EmployerDao employerDao;
	private EmployeeDao employeeDao;

	@Autowired
	public AuthManager(UnemployedService unemployedService, EmployerService employerService,
			VerificationService verificationService, UserDao userDao, UnemployedDao unemployedDao,
			EmployerDao employerDao, EmployeeDao employeeDao) {
		super();
		this.unemployedService = unemployedService;
		this.employerService = employerService;
		this.verificationService = verificationService;
		this.userDao = userDao;
		this.unemployedDao = unemployedDao;
		this.employerDao = employerDao;
		this.employeeDao = employeeDao;
	}

	@Override
	public Result confirmPassword(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return new SuccessResult("Şifre doğrulandı.");
		}
		return new ErrorResult("Şifreler uyuşmuyor.");
	}

	@Override
	public DataResult<User> checkEmail(String email) {
		if(this.userDao.findByEmail(email) != null) {
			return new ErrorDataResult<User>("E-Posta adresi mevcut.");
		}
		return new SuccessDataResult<User>(this.userDao.findByEmail(email));
	}

	@Override
	public DataResult<Employer> registerEmployer(Employer employer, String confirmPassword) {
		if (this.checkEmail(employer.getEmail()).isSuccess()) {
			if (this.confirmPassword(employer.getPassword(), confirmPassword).isSuccess()) {
				this.employerService.addEmployer(employer);
				this.verificationService.addUser(employer.getId());
				return new SuccessDataResult<Employer>(employer, "Kayıt işlemi başarılı.");
			}
		}
		return new ErrorDataResult<Employer>("Kayıt olma başarısız.");
	}

	@Override
	public DataResult<Unemployed> registerUnemployed(Unemployed unemployed, String confirmPassword) {
		if (this.checkEmail(unemployed.getEmail()).isSuccess()) {
			if (this.confirmPassword(unemployed.getPassword(), confirmPassword).isSuccess()) {
				this.unemployedService.addUnemployed(unemployed);
				this.verificationService.addUser(unemployed.getId());
				return new SuccessDataResult<Unemployed>(unemployed, "Kayıt işlemi başarılı.");
			}
		}
		return new ErrorDataResult<Unemployed>("Kayıt olma başarısız.");
	}

	@Override
	public DataResult<LoginReturnDto> login(LoginDto loginDto) {
		User user = this.userDao.findByEmail(loginDto.getEmail().toString());
		if (user == null) {
			return new ErrorDataResult<LoginReturnDto>("Email adresi hatalı veya kayıtlı değil.");
		} else if (!user.getPassword().equals(loginDto.getPassword())) {
			return new ErrorDataResult<LoginReturnDto>("Hatalı şifre girdiniz.");
		} else if (!user.getMailIsVerify()) {
			return new ErrorDataResult<LoginReturnDto>("Giriş yapmak için Email adresinizi onaylamanız gerekiyor.");
		}

		LoginReturnDto loginReturnDto = new LoginReturnDto();
		loginReturnDto.setId(user.getId());
		loginReturnDto.setEmail(user.getEmail());

		if (this.unemployedDao.existsById(user.getId())) {
			loginReturnDto.setUserType(1);
			loginReturnDto.setName(this.unemployedDao.getOne(user.getId()).getFirstName() + " "
					+ this.unemployedDao.getOne(user.getId()).getLastName());
		} else if (this.employerDao.existsById(user.getId())) {
			loginReturnDto.setUserType(2);
			loginReturnDto.setName(this.employerDao.getOne(user.getId()).getCompanyName());
		} else if (this.employeeDao.existsById(user.getId())) {
			loginReturnDto.setUserType(3);
			loginReturnDto.setName(this.employeeDao.getOne(user.getId()).getFirstName() + " "
					+ this.employeeDao.getOne(user.getId()).getLastName());
		} else {
			return new ErrorDataResult<LoginReturnDto>("Giriş başarısız. Bilgileri kontrol edip tekrar deneyiniz.");
		}

		return new SuccessDataResult<LoginReturnDto>(loginReturnDto, "Giriş Yapıldı");
	}
}
