package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.ConfirmingEmployer;
import kilobyte.hrms.entities.concretes.Employer;

public interface ConfirmingEmployerService {

	Result verify (int employeeId, int employerId, boolean status);
	DataResult<List<ConfirmingEmployer>> getAll();
	DataResult<List<Employer>> getByEmployerIsConfirmed(boolean status);
}
