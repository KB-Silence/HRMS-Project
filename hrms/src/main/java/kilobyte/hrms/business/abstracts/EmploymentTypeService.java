package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.EmploymentType;

public interface EmploymentTypeService {

	DataResult<List<EmploymentType>> getAll();
}
