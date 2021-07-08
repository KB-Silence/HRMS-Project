package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.LanguageService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.LanguageDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.Language;
import kilobyte.hrms.entities.dtos.LanguageDto;

@Service
public class LanguageManager implements LanguageService {

	private LanguageDao languageDao;
	private UnemployedDao unemployedDao;
	
	@Autowired
	public LanguageManager(LanguageDao languageDao, UnemployedDao unemployedDao) {
		super();
		this.languageDao = languageDao;
		this.unemployedDao = unemployedDao;
	}
	
	@Override
	public Result addLanguage(LanguageDto languageDto) {
		Language language = new Language();
		language.setLanguageName(languageDto.getLanguageName());
		language.setLanguageLevel(languageDto.getLanguageLevel());
		language.setUnemployed(this.unemployedDao.getOne(languageDto.getUnemployedId()));
		
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

	@Override
	public Result deleteLanguage(int languageId) {
		if(!this.languageDao.existsById(languageId)) {
			return new ErrorResult("Dil bilgisi bulunamadı.");
		}
		return new SuccessResult("Dil bilgisi silindi.");
	}

}
