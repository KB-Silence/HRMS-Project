package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.JobExperienceService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.JobExperienceDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.JobExperience;
import kilobyte.hrms.entities.dtos.JobExperienceDto;

@Service
public class JobExperienceManager implements JobExperienceService {

	private JobExperienceDao jobExperienceDao;
	private UnemployedDao unemployedDao;

	@Autowired
	public JobExperienceManager(JobExperienceDao jobExperienceDao, UnemployedDao unemployedDao) {
		super();
		this.jobExperienceDao = jobExperienceDao;
		this.unemployedDao = unemployedDao;
	}

	@Override
	public Result addJobExperience(JobExperienceDto jobExperienceDto) {

		JobExperience experience = new JobExperience();
		experience.setWorkplaceName(jobExperienceDto.getWorkplaceName());
		experience.setPositionName(jobExperienceDto.getPositionName());
		experience.setStartDate(jobExperienceDto.getStartDate());
		experience.setLeaveDate(jobExperienceDto.getLeaveDate());
		experience.setUnemployed(this.unemployedDao.getOne(jobExperienceDto.getUnemployedId()));

		this.jobExperienceDao.save(experience);
		return new SuccessResult("İş tecrübesi eklendi.");
	}

	@Override
	public Result deleteJobExperience(int experienceId) {
		if (!this.jobExperienceDao.existsById(experienceId)) {
			return new ErrorResult("İş tecrübesi bilgileri bulunamadı.");
		}
		this.jobExperienceDao.deleteById(experienceId);
		return new SuccessResult("İş tecrübesi bilgileri silindi.");
	}

	@Override
	public DataResult<List<JobExperience>> getAll() {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(),
				"İş tecrübeleri listelendi.");
	}

	@Override
	public DataResult<List<JobExperience>> getByUnemployedIdOrderByLeaveDateDesc(int unemployedId) {
		return new SuccessDataResult<List<JobExperience>>(
				this.jobExperienceDao.getByUnemployed_UserIdOrderByLeaveDateDesc(unemployedId), "Devam ediyor.");
	}

	@Override
	public Result updateJobExperience(JobExperienceDto jobExperienceDto, int experienceId) {
		JobExperience jobExperience = this.jobExperienceDao.getOne(experienceId);
		if (jobExperience.getWorkplaceName() == jobExperienceDto.getWorkplaceName()
				&& jobExperience.getPositionName() == jobExperienceDto.getPositionName()
				&& jobExperience.getStartDate() == jobExperienceDto.getStartDate()
				&& jobExperience.getLeaveDate() == jobExperienceDto.getLeaveDate()) {
			return new ErrorResult("Değişiklik yapmadınız.");
		}
		jobExperience.setWorkplaceName(jobExperienceDto.getWorkplaceName());
		jobExperience.setPositionName(jobExperienceDto.getPositionName());
		jobExperience.setStartDate(jobExperienceDto.getStartDate());
		jobExperience.setLeaveDate(jobExperienceDto.getLeaveDate());
		this.jobExperienceDao.save(jobExperience);
		return new SuccessResult("İş tecrübesi bilgileri güncellendi.");
	}

}
