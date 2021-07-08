package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.JobAdvertisement;
import kilobyte.hrms.entities.dtos.JobAdvertFilterDto;
import kilobyte.hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementService {
	
	Result add(JobAdvertisementDto advertisementDto);
	Result changeAdvertisementStatus(int advertId, boolean status);
	
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<List<JobAdvertisement>> getAllApproved(boolean status);
	DataResult<List<JobAdvertisement>> getByAdvertStatus();
	DataResult<List<JobAdvertisement>> getByAdvertStatusAndSorted();
	DataResult<List<JobAdvertisement>> getByAdvertStatusTrueAndEmployerId(int employerId);
	DataResult<List<JobAdvertisement>> getByAdvertIsConfirmedAndPageNumberAndFilter(int pageNo, int pageSize, JobAdvertFilterDto filterDto);
}
