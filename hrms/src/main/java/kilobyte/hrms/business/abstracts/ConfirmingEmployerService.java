package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.ConfirmingEmployer;
import kilobyte.hrms.entities.concretes.Employer;

public interface ConfirmingEmployerService {

	Result verifyEmployer (int employeeId, int employerId);
	DataResult<List<ConfirmingEmployer>> getAll();
	DataResult<List<Employer>> getByEmployerIsConfirmedFalse();
}
