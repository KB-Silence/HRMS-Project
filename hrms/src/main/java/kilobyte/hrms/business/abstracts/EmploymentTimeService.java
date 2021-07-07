package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.EmploymentTime;

public interface EmploymentTimeService {
	
	DataResult<List<EmploymentTime>> getAll();
}
