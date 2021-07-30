package kilobyte.hrms.business.concretes;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.VerificationService;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.UserDao;
import kilobyte.hrms.dataAccess.abstracts.VerificationDao;
import kilobyte.hrms.entities.concretes.User;
import kilobyte.hrms.entities.concretes.Verification;

@Service
public class VerificationManager implements VerificationService {

	private VerificationDao verificationDao;
	private UserDao userDao;

	@Autowired
	public VerificationManager(VerificationDao verificationDao, UserDao userDao) {
		super();
		this.verificationDao = verificationDao;
		this.userDao = userDao;
	}

	@Override
	public Result verifyUser(String code) {
		if(this.verificationDao.getByVerificationCode(code) != null ) {
			User user = this.userDao.getByUserId(verificationDao.getByVerificationCode(code).getUserId());
			Verification verify = this.verificationDao.getByUserId(user.getUserId());
			if (!user.getMailIsVerify()) {
				user.setMailIsVerify(true);
				verify.setVerified(true);
				this.userDao.save(user);
				this.verificationDao.save(verify);
				return new SuccessResult("Kullanıcı başarıyla doğrulandı.");
			}
		}
		return new ErrorResult("Doğrulama işlemi başarısız");
	}

	@Override
	public String generateCode(User user) {	
		UUID code = UUID.randomUUID();
		Verification verify = new Verification();
		verify.setVerificationCode(code.toString());
		verify.setUserId(user.getUserId());
		this.verificationDao.save(verify);
		return code.toString();
	}

}
