package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Technology;
import kilobyte.hrms.entities.dtos.TechnologyDto;

public interface TechnologyService {
	Result addTechnology(TechnologyDto technologyDto);
	Result deleteTechnology(int technologyId);
	DataResult<List<Technology>> getAll();
	DataResult<List<Technology>> getByUnemployedId(int unemployedId);
}
