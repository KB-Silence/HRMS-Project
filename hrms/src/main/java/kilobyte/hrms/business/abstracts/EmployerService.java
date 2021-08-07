package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Employer;
import kilobyte.hrms.entities.concretes.EmployerUpdate;
import kilobyte.hrms.entities.dtos.EmployerRegisterDto;

public interface EmployerService {
	
	Result addEmployer(EmployerRegisterDto employerDto);
	Result updateEmployer(EmployerUpdate employerUpdate);
	Result checkEmailDomain(String email, String domain);
	DataResult<Employer> getByUserId(int userId);
	DataResult<List<Employer>> getAll();
	DataResult<List<Employer>> getByMailIsVerifyTrue();
}
