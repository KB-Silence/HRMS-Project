package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.JobExperienceService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.JobExperienceDao;
import kilobyte.hrms.entities.concretes.JobExperience;

@Service
public class JobExperienceManager implements JobExperienceService{

	private JobExperienceDao jobExperienceDao;
	
	public JobExperienceManager(JobExperienceDao jobExperienceDao) {
		super();
		this.jobExperienceDao = jobExperienceDao;
	}
	
	@Override
	public Result addJobExperience(JobExperience jobExperience) {
		this.jobExperienceDao.save(jobExperience);
		return new SuccessResult("İş tecrübesi eklendi.");
	}

	@Override
	public DataResult<List<JobExperience>> getAll() {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(),"İş tecrübeleri listelendi.");
	}

	@Override
	public DataResult<List<JobExperience>> getByUnemployedIdOrderByLeaveDateDesc(int unemployedId) {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.getByUnemployedIdOrderByLeaveDateDesc(unemployedId),"Devam ediyor.");
	}
	
}
