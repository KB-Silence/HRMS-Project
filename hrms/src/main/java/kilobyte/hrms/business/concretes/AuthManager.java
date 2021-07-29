package kilobyte.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.AuthService;
import kilobyte.hrms.business.abstracts.EmployerService;
import kilobyte.hrms.business.abstracts.UnemployedService;
import kilobyte.hrms.business.abstracts.VerificationService;
import kilobyte.hrms.core.abstracts.EmailService;
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
import kilobyte.hrms.entities.concretes.User;
import kilobyte.hrms.entities.dtos.EmployerRegisterDto;
import kilobyte.hrms.entities.dtos.LoginDto;
import kilobyte.hrms.entities.dtos.LoginReturnDto;
import kilobyte.hrms.entities.dtos.UnemployedRegisterDto;

@Service
public class AuthManager implements AuthService {

	private UnemployedService unemployedService;
	private EmployerService employerService;
	private VerificationService verificationService;
	private EmailService emailService;

	private UserDao userDao;
	private UnemployedDao unemployedDao;
	private EmployerDao employerDao;
	private EmployeeDao employeeDao;

	@Autowired
	public AuthManager(UnemployedService unemployedService, EmployerService employerService,
			VerificationService verificationService, UserDao userDao, UnemployedDao unemployedDao,
			EmployerDao employerDao, EmployeeDao employeeDao, EmailService emailService) {
		super();
		this.unemployedService = unemployedService;
		this.employerService = employerService;
		this.verificationService = verificationService;
		this.emailService = emailService;

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
	public Result checkEmail(String email) {
		if (this.userDao.findByEmail(email) == null) {
			return new SuccessResult();
		}
		return new ErrorResult("E-posta mevcut.");
	}

	@Override
	public DataResult<EmployerRegisterDto> registerEmployer(EmployerRegisterDto employerDto, String confirmPassword) {
		
		if (!this.checkEmail(employerDto.getEmail()).isSuccess()) {
			return new ErrorDataResult<EmployerRegisterDto>("E-posta adresi daha önce alınmış.");
		}
		if (this.confirmPassword(employerDto.getPassword(), confirmPassword).isSuccess()) {
			this.employerService.addEmployer(employerDto);
			User user = this.employerDao.getByEmail(employerDto.getEmail());
			this.emailService.sendVerifyEmail(user, this.verificationService.generateCode(user));
			return new SuccessDataResult<EmployerRegisterDto>(employerDto,
					"Kayıt işlemi başarılı. Mail adresine doğrulama bağlantısı gönderildi.");
		}
		return new ErrorDataResult<EmployerRegisterDto>(
				"Şifreler uyuşmuyor. Kontrol edip tekrar deneyiniz.");
	}

	@Override
	public DataResult<UnemployedRegisterDto> registerUnemployed(UnemployedRegisterDto unemployedDto,
			String confirmPassword) {
		if (!this.checkEmail(unemployedDto.getEmail()).isSuccess()) {
			return new ErrorDataResult<UnemployedRegisterDto>("E-posta adresi daha önce alınmış.");
		}
			if (this.confirmPassword(unemployedDto.getPassword(), confirmPassword).isSuccess()) {
				this.unemployedService.addUnemployed(unemployedDto);
				User user = this.unemployedDao.getByEmail(unemployedDto.getEmail());
				this.emailService.sendVerifyEmail(user, this.verificationService.generateCode(user));
				return new SuccessDataResult<UnemployedRegisterDto>(unemployedDto,
						"Kayıt işlemi başarılı. Mail adresine doğrulama bağlantısı gönderildi.");
			}
		return new ErrorDataResult<UnemployedRegisterDto>(
				"Kayıt olma başarısız. Bilgileri kontrol edip tekrar deneyin lütfen.");
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
