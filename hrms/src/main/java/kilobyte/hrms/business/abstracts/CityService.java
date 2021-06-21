package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.City;

public interface CityService {
	
	DataResult<List<City>> getAll();

}
