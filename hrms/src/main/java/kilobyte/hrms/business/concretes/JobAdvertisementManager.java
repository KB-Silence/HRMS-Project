package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.JobAdvertisementService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kilobyte.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao advertisementDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao advertisementDao) {
		super();
		this.advertisementDao = advertisementDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.findAll());
	}

	@Override
	public Result add(JobAdvertisement advertisement) {
		this.advertisementDao.save(advertisement);
		return new SuccessResult("Yeni iş ilanı eklendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActive() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getAllByActive(),
				"Data listelendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllByActiveSorted() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getAllByActiveSorted(),
				"Data Listelendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllActiveByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getAllActiveByEmployerId(employerId),
				"Data Listelendi.");
	}

	@Override
	public Result changeAdvertisementStatus(int advertId, int employerId, boolean status) {
		JobAdvertisement advertisement = this.advertisementDao.getByAdvertIdAndEmployerId(advertId, employerId);
		advertisement.setAdvertStatus(status);
		this.advertisementDao.save(advertisement);
		return new SuccessResult("İş ilanı durumu değiştirildi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllApproved(boolean verified) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getAllByConfirmingJobAdvertisement_VerifiedStatus(verified));
	}

}
