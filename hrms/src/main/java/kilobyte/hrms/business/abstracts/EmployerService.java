package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Employer;

public interface EmployerService {
	
	Result addEmployer(Employer employer);
	Result checkEmailDomain(String email, String domain);
	DataResult<List<Employer>> getAll();
}
