package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.CoverLetter;

public interface CoverLetterService {
	Result addCoverLetter(CoverLetter coverLetter);
	DataResult<List<CoverLetter>> getAll();
	DataResult<List<CoverLetter>> getByUnemployedId(int unemployedId);
}
