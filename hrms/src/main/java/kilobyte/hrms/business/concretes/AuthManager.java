package kilobyte.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.AuthService;
import kilobyte.hrms.business.abstracts.EmployerService;
import kilobyte.hrms.business.abstracts.UnemployedService;
import kilobyte.hrms.business.abstracts.VerificationService;
import kilobyte.hrms.core.abstracts.EmployerVerifyService;
import kilobyte.hrms.core.abstracts.MernisService;
import kilobyte.hrms.core.abstracts.UnemployedVerifyService;
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
	private MernisService mernisService;
	private VerificationService verificationService;
	private EmployerVerifyService employerVerifyService;
	private UnemployedVerifyService unemployedVerifyService;

	private UserDao userDao;
	private UnemployedDao unemployedDao;
	private EmployerDao employerDao;
	private EmployeeDao employeeDao;

	@Autowired
	public AuthManager(UnemployedService unemployedService, EmployerService employerService,
			MernisService mernisService, VerificationService verificationService,
			EmployerVerifyService employerVerifyService, UnemployedVerifyService unemployedVerifyService,
			UserDao userDao, UnemployedDao unemployedDao, EmployerDao employerDao, EmployeeDao employeeDao) {
		super();
		this.unemployedService = unemployedService;
		this.employerService = employerService;
		this.mernisService = mernisService;
		this.verificationService = verificationService;
		this.employerVerifyService = employerVerifyService;
		this.unemployedVerifyService = unemployedVerifyService;
		this.userDao = userDao;
		this.unemployedDao = unemployedDao;
		this.employerDao = employerDao;
		this.employeeDao = employeeDao;
	}

	@Override
	public DataResult<Unemployed> registerUnemployed(Unemployed unemployed, String confirmPassword) {
		if (!this.mernisControl(unemployed)) {
			return new ErrorDataResult<Unemployed>("Kimlik doğrulaması başarısız.");
		}

		if (this.confrimPassword(unemployed.getPassword(), confirmPassword).isSuccess()) {
			this.unemployedService.add(unemployed);
			this.verificationService.addUser(unemployed.getId());
			this.unemployedVerifyService.verifyByCode(this.verificationService.generateCode(), unemployed.getEmail());
			return new SuccessDataResult<Unemployed>(unemployed, "Kayıt işlemi başarılı.");
		}
		return new ErrorDataResult<Unemployed>("Kayıt olma başarısız.");
	}

	@Override
	public DataResult<Employer> registerEmployer(Employer employer, String confirmPassword) {
		if (this.confrimPassword(employer.getPassword(), confirmPassword).isSuccess()) {
			if (this.checkEmailDomain(employer.getEmail(), employer.getWebSite()).isSuccess()) {
				this.employerService.add(employer);
				this.verificationService.addUser(employer.getId());
				this.employerVerifyService.verifyEmployerByEmployee(employer);
				return new SuccessDataResult<Employer>(employer, "Kayıt işlemi başarılı.");
			}
			return new ErrorDataResult<Employer>(employer, "Domain eşleştirme başarısız.");
		}
		return new ErrorDataResult<Employer>("Kayıt olma başarısız.");
	}

	private Result confrimPassword(String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			return new SuccessResult("Şifre doğrulandı.");
		}
		return new ErrorResult("Şifreler uyuşmuyor.");
	}

	private Result checkEmailDomain(String email, String website) {
		String[] mails = email.split("@", 2);
		String web = website.substring(4);
		if (mails[1].equals(web)) {
			return new SuccessResult("Domain kontrolü başarılı.");
		}
		return new ErrorResult("Domain kontrolü başarısız.");
	}

	private boolean mernisControl(Unemployed unemployed) {
		boolean result = this.mernisService.checkIfRealPerson(unemployed);
		return result;
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
			loginReturnDto.setName(this.unemployedDao.getOne(user.getId()).getFirstName() + " " + this.unemployedDao.getOne(user.getId()).getLastName());
		} else if (this.employerDao.existsById(user.getId())) {
			loginReturnDto.setUserType(2);
			loginReturnDto.setName(this.employerDao.getOne(user.getId()).getCompanyName());
		} else if (this.employeeDao.existsById(user.getId())) {
			loginReturnDto.setUserType(3);
			loginReturnDto.setName(this.employeeDao.getOne(user.getId()).getFirstName() + " " + this.employeeDao.getOne(user.getId()).getLastName());
		} else {
			return new ErrorDataResult<LoginReturnDto>("Giriş başarısız. Bilgileri kontrol edip tekrar deneyiniz.");
		}

		return new SuccessDataResult<LoginReturnDto>(loginReturnDto, "Giriş Yapıldı");
	}

}
