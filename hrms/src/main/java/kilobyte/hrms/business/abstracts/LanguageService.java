package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Language;

public interface LanguageService {
	Result addLanguage(Language language);
	DataResult<List<Language>> getAll();
	DataResult<List<Language>> getByUnemployedId(int unemployedId);
}
