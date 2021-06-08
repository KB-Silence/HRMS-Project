package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.LanguageService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.LanguageDao;
import kilobyte.hrms.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {

	private LanguageDao languageDao;
	
	public LanguageManager(LanguageDao languageDao) {
		super();
		this.languageDao = languageDao;
	}
	
	@Override
	public Result addLanguage(Language language) {
		this.languageDao.save(language);
		return new SuccessResult("Dil eklendi.");
	}

	@Override
	public DataResult<List<Language>> getAll() {
		return new SuccessDataResult<List<Language>>(this.languageDao.findAll(),"Diller listelendi.");
	}

	@Override
	public DataResult<List<Language>> getByUnemployedId(int unemployedId) {
		return new SuccessDataResult<List<Language>>(this.languageDao.getByUnemployedId(unemployedId),"İş arayanın dil bilgileri listelendi.");
	}

}