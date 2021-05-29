package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Unemployed;

public interface UnemployedService {
	
	Result add(Unemployed unemployed);
	DataResult<List<Unemployed>> getAll();
}
