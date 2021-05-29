package kilobyte.hrms.business.abstracts;

import kilobyte.hrms.core.utilities.results.Result;

public interface VerificationService {
	
	Result addUser(int userId);
	Result addEmployer(int employeeId, int employerId);
	String generateCode();

}
