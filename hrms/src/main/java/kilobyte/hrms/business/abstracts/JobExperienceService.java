package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.JobExperience;

public interface JobExperienceService {
	
	Result addJobExperience(JobExperience jobExperience);
	Result deleteJobExperience(int experienceId);
	DataResult<List<JobExperience>> getAll();
	DataResult<List<JobExperience>> getByUnemployedIdOrderByLeaveDateDesc(int unemployedId);
}
