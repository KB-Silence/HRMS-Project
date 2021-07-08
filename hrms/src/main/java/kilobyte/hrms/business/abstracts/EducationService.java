package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Education;
import kilobyte.hrms.entities.dtos.EducationDto;

public interface EducationService {
	
	Result addEducation(EducationDto educationDto);
	Result deleteEducation(int educationId);
	DataResult<List<Education>> getAll();
	DataResult<List<Education>> getByUnemployedIdOrderByGraduatedDateDesc(int unemployedId);
}
