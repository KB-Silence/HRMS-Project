package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Language;
import kilobyte.hrms.entities.dtos.LanguageDto;

public interface LanguageService {
	Result addLanguage(LanguageDto languageDto);
	Result deleteLanguage(int languageId);
	DataResult<List<Language>> getAll();
	DataResult<List<Language>> getByUnemployedId(int unemployedId);
}
