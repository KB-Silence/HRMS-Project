package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;
import kilobyte.hrms.entities.concretes.JobAdvertisement;

public interface ConfirmingJobAdvertisementService {

	Result verifyAdvertisement (int employeeId, int advertId, boolean status);
	DataResult<List<ConfirmingJobAdvertisement>> getAll();
	DataResult<List<JobAdvertisement>> getByAdvertIsConfirmedFalse();
	
}
