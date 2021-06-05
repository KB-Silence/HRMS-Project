package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Technology;

public interface TechnologyService {
	Result addTechnology(Technology technology);
	DataResult<List<Technology>> getAll();
	DataResult<List<Technology>> getByUnemployedId(int unemployedId);
}
