package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.ConfirmingUpdate;
import kilobyte.hrms.entities.concretes.Employer;
import kilobyte.hrms.entities.concretes.EmployerUpdate;

public interface ConfirmingUpdateService {

	Result verifyUpdate(int employeeId, int employerId, int updateId, boolean status);
	DataResult<List<ConfirmingUpdate>> getAll();
	DataResult<List<EmployerUpdate>> getByApproveStatus(boolean status);
	DataResult<List<Employer>> getByWaitingForUpdate(boolean status);
}
