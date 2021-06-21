package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.ConfirmingEmployer;

public interface ConfirmingEmployerService {

	Result verify (ConfirmingEmployer confirmEmployer);
	DataResult<List<ConfirmingEmployer>> getAll();
}
