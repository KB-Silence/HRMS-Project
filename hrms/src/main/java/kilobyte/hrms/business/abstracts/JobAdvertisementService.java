package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.JobAdvertisement;
import kilobyte.hrms.entities.dtos.JobAdvertFilterDto;
import kilobyte.hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementService {
	
	Result add(JobAdvertisementDto advertisementDto);
	Result changeAdvertisementStatus(int advertId);
	
	DataResult<JobAdvertisement> getByAdvertId(int advertId);
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<List<JobAdvertisement>> getByAdvertStatusTrue();
	DataResult<List<JobAdvertisement>> getByAdvertStatusAndEmployerIdSorted(int employerId);
	DataResult<List<JobAdvertisement>> getByAdvertIsConfirmed(boolean status);
	DataResult<List<JobAdvertisement>> getByAdvertStatusTrueAndEmployerId(int employerId);
	DataResult<List<JobAdvertisement>> getByAdvertStatusAndAdvertIsConfirmedAndEmployerId(int employerId);
	DataResult<List<JobAdvertisement>> getByAdvertStatusFalseAndAdvertIsConfirmedTrueAndEmployerId(int employerId);
	DataResult<List<JobAdvertisement>> getByAdvertIsConfirmedAndPageNumberAndFilter(int pageNo, int pageSize, JobAdvertFilterDto filterDto);
	
}
