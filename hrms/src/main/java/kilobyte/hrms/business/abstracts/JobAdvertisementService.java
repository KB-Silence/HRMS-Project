package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
	
	Result add(JobAdvertisement advertisement);
	Result changeAdvertisementStatus(int advertId, int employerId, boolean status);
	
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<List<JobAdvertisement>> getAllApproved(boolean verified);
	DataResult<List<JobAdvertisement>> getAllByActive();
	DataResult<List<JobAdvertisement>> getAllByActiveSorted();
	DataResult<List<JobAdvertisement>> getAllActiveByEmployerId(int employerId);
}
