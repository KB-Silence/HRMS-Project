package kilobyte.hrms.business.abstracts;

import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.User;

public interface VerificationService {
	
	Result verifyUser(String code);
	String generateCode(User user);

}
