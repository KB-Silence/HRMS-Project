package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.CoverLetter;
import kilobyte.hrms.entities.dtos.CoverLetterDto;

public interface CoverLetterService {
	Result addCoverLetter(CoverLetterDto coverLetterDto);
	Result updateCoverLetter(CoverLetterDto coverLetterDto);
	DataResult<List<CoverLetter>> getAll();
	DataResult<CoverLetter> getByUnemployedId(int unemployedId);
}
