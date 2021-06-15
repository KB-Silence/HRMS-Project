package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;

public interface ConfirmingJobAdvertisementService {

	Result verify (ConfirmingJobAdvertisement confirmJobAdvert);
	DataResult<List<ConfirmingJobAdvertisement>> getAll();
	
}
