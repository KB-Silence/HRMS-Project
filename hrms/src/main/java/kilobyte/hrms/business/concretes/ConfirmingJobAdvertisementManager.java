package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.ConfirmingJobAdvertisementService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.ConfirmingJobAdvertisementDao;
import kilobyte.hrms.dataAccess.abstracts.EmployeeDao;
import kilobyte.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;
import kilobyte.hrms.entities.concretes.JobAdvertisement;

@Service
public class ConfirmingJobAdvertisementManager implements ConfirmingJobAdvertisementService {

	private EmployeeDao employeeDao;
	private ConfirmingJobAdvertisementDao confirmingJobAdvertisementDao;
	private JobAdvertisementDao advertisementDao;

	@Autowired
	public ConfirmingJobAdvertisementManager(ConfirmingJobAdvertisementDao confirmingJobAdvertisementDao,
			JobAdvertisementDao advertisementDao, EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
		this.confirmingJobAdvertisementDao = confirmingJobAdvertisementDao;
		this.advertisementDao = advertisementDao;
	}

	@Override
	public Result verifyAdvertisement(int employeeId, int advertId, boolean status) {

		ConfirmingJobAdvertisement confirmAdvert = new ConfirmingJobAdvertisement();
		JobAdvertisement advertisement = this.advertisementDao.getOne(advertId);
		confirmAdvert.setEmployee(this.employeeDao.getOne(employeeId));
		confirmAdvert.setJobAdvertisement(this.advertisementDao.getOne(advertId));
		confirmAdvert.setVerifiedStatus(status);
		advertisement.setAdvertStatus(status);
		advertisement.setAdvertIsConfirmed(status);
		this.advertisementDao.save(advertisement);
		this.confirmingJobAdvertisementDao.save(confirmAdvert);

		if (status) {
			return new SuccessResult("İlanı onayladınız.");
		} else {
			return new SuccessResult("İlanı onaylamadınız.");
		}

	}

	@Override
	public DataResult<List<ConfirmingJobAdvertisement>> getAll() {
		return new SuccessDataResult<List<ConfirmingJobAdvertisement>>(this.confirmingJobAdvertisementDao.findAll());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByAdvertIsConfirmedFalse() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getByAdvertIsConfirmedFalse(),
				"Onaylanmayanlar listelendi.");
	}

}
