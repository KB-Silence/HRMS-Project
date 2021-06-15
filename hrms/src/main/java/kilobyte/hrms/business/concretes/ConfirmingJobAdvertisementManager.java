package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.ConfirmingJobAdvertisementService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.ConfirmingJobAdvertisementDao;
import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;

@Service
public class ConfirmingJobAdvertisementManager implements ConfirmingJobAdvertisementService{

	private ConfirmingJobAdvertisementDao confirmingJobAdvertisementDao;
	
	public ConfirmingJobAdvertisementManager(ConfirmingJobAdvertisementDao confirmingJobAdvertisementDao) {
		super();
		this.confirmingJobAdvertisementDao = confirmingJobAdvertisementDao;
	}
	
	@Override
	public Result verify(ConfirmingJobAdvertisement confirmJobAdvert) {
		this.confirmingJobAdvertisementDao.save(confirmJobAdvert);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<ConfirmingJobAdvertisement>> getAll() {
		return new SuccessDataResult<List<ConfirmingJobAdvertisement>>(this.confirmingJobAdvertisementDao.findAll());
	}

}
