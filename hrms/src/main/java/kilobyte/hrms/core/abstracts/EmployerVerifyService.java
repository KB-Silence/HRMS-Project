package kilobyte.hrms.core.abstracts;

import kilobyte.hrms.entities.concretes.Employer;

public interface EmployerVerifyService {
	void verifyEmployerByEmployee(Employer employer);
	void verifyByCode(String code, String email);
}
