package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Education;

public interface EducationService {
	
	Result addEducation(Education education);
	Result deleteEducation(int educationId);
	DataResult<List<Education>> getAll();
	DataResult<List<Education>> getByUnemployedIdOrderByGraduatedDateDesc(int unemployedId);
}
