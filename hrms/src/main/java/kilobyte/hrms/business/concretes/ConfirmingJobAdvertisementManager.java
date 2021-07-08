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
	public Result verify(int employeeId, int advertId, boolean status) {

		ConfirmingJobAdvertisement confirmAdvert = new ConfirmingJobAdvertisement();
		JobAdvertisement advertisement = this.advertisementDao.getOne(advertId);

		confirmAdvert.setEmployee(this.employeeDao.getOne(employeeId));
		confirmAdvert.setJobAdvertisement(this.advertisementDao.getOne(advertId));
		confirmAdvert.setVerifiedStatus(status);
		
		advertisement.setAdvertIsConfirmed(true);
		
		this.confirmingJobAdvertisementDao.save(confirmAdvert);
		this.advertisementDao.save(advertisement);
		
		return new SuccessResult("İlan onaylandı.");
	}

	@Override
	public DataResult<List<ConfirmingJobAdvertisement>> getAll() {
		return new SuccessDataResult<List<ConfirmingJobAdvertisement>>(this.confirmingJobAdvertisementDao.findAll());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByAdvertIsConfirmed(boolean status) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getByAdvertIsConfirmed(status), "Onaylanmayanlar listelendi.");
	}

}
