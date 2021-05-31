package kilobyte.hrms.business.concretes;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.VerificationService;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.ConfirmationByEmployeeDao;
import kilobyte.hrms.dataAccess.abstracts.VerificationDao;
import kilobyte.hrms.entities.concretes.Verification;
import kilobyte.hrms.entities.concretes.ConfirmingEmployer;



@Service
public class VerificationManager implements VerificationService{

	private VerificationDao verificationDao;
	private ConfirmationByEmployeeDao verificationByEmployee;
	
	@Autowired
	public VerificationManager(VerificationDao verificationDao, ConfirmationByEmployeeDao verificationByEmployee) {
		super();
		this.verificationDao = verificationDao;
		this.verificationByEmployee = verificationByEmployee;
	}

	@Override
	public Result addUser(int userId) {
		Verification verifyCode = new Verification();
		verifyCode.setVerified(true);
		verifyCode.setVerificationCode(this.generateCode());
		verifyCode.setUserId(userId);
		this.verificationDao.save(verifyCode);
		return new SuccessResult();
	}

	@Override
	public Result addEmployer(int employeeId, int employerId) {
		ConfirmingEmployer verifyBy = new ConfirmingEmployer();
		verifyBy.setEmployeeId(employeeId);
		verifyBy.setEmployerId(employerId);
		verifyBy.setVerifiedStatus(true);
		this.verificationByEmployee.save(verifyBy);
		return new SuccessResult();
	}
	
	public String generateCode() {
		UUID code = UUID.randomUUID();
		return code.toString();
	}


}
