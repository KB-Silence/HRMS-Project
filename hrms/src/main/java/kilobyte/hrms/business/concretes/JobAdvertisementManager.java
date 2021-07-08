package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.JobAdvertisementService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.CityDao;
import kilobyte.hrms.dataAccess.abstracts.EmployerDao;
import kilobyte.hrms.dataAccess.abstracts.EmploymentTimeDao;
import kilobyte.hrms.dataAccess.abstracts.EmploymentTypeDao;
import kilobyte.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kilobyte.hrms.dataAccess.abstracts.PositionDao;
import kilobyte.hrms.entities.concretes.JobAdvertisement;
import kilobyte.hrms.entities.dtos.JobAdvertFilterDto;
import kilobyte.hrms.entities.dtos.JobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao advertisementDao;
	private CityDao cityDao;
	private EmploymentTimeDao timeDao;
	private EmploymentTypeDao typeDao;
	private PositionDao positionDao;
	private EmployerDao employerDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao advertisementDao, CityDao cityDao, EmploymentTimeDao timeDao,
			EmploymentTypeDao typeDao, PositionDao positionDao, EmployerDao employerDao) {
		super();
		this.advertisementDao = advertisementDao;
		this.cityDao = cityDao;
		this.timeDao = timeDao;
		this.typeDao = typeDao;
		this.positionDao = positionDao;
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		Sort sort = Sort.by(Sort.Direction.ASC,"advertId");
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.findAll(sort));
	}

	@Override
	public Result add(JobAdvertisementDto advertisementDto) {

		JobAdvertisement jobAdvertisement = new JobAdvertisement();

		jobAdvertisement.setMinSalary(advertisementDto.getMinSalary());
		jobAdvertisement.setMaxSalary(advertisementDto.getMaxSalary());
		jobAdvertisement.setQuota(advertisementDto.getQuota());
		jobAdvertisement.setJobDescription(advertisementDto.getDescription());
		jobAdvertisement.setLastApplication(advertisementDto.getLastApplication());

		jobAdvertisement.setCity(this.cityDao.getOne(advertisementDto.getCityId()));
		jobAdvertisement.setEmploymentTime(this.timeDao.getOne(advertisementDto.getTimeId()));
		jobAdvertisement.setEmploymentType(this.typeDao.getOne(advertisementDto.getTypeId()));
		jobAdvertisement.setPosition(this.positionDao.getOne(advertisementDto.getPositionId()));
		jobAdvertisement.setEmployer(this.employerDao.getOne(advertisementDto.getEmployerId()));

		this.advertisementDao.save(jobAdvertisement);
		return new SuccessResult("Yeni iş ilanı eklendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByAdvertStatus() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getByAdvertStatusTrueOrderByAdvertIdAsc(),
				"Data listelendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByAdvertStatusAndSorted() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getByAdvertStatusTrueOrderByCreatedDateAsc(),
				"Data Listelendi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByAdvertStatusTrueAndEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.advertisementDao.getByAdvertStatusTrueAndEmployerId(employerId),
				"Data Listelendi.");
	}

	@Override
	public Result changeAdvertisementStatus(int advertId, boolean status) {
		JobAdvertisement advertisement = this.advertisementDao.getByAdvertId(advertId);
		advertisement.setAdvertStatus(status);
		this.advertisementDao.save(advertisement);
		return new SuccessResult("İş ilanı durumu değiştirildi.");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllApproved(boolean status) {
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.advertisementDao.getByAdvertIsConfirmed(status));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByAdvertIsConfirmedAndPageNumberAndFilter(int pageNo, int pageSize,
			JobAdvertFilterDto filterDto) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return new SuccessDataResult<List<JobAdvertisement>>(
				this.advertisementDao.getByFilter(filterDto, pageable).getContent(),
				this.advertisementDao.getByFilter(filterDto, pageable).getTotalElements() + "");
	}

}
