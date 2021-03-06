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
		if (this.languageDao.getByLanguageNameAndUnemployed_UserId(languageDto.getLanguageName(),
				languageDto.getUnemployedId()) != null) {
			return new ErrorResult("Bu dili zaten eklemişsiniz.");
		}
		language.setLanguageName(languageDto.getLanguageName());
		language.setLanguageLevel(languageDto.getLanguageLevel());
		language.setUnemployed(this.unemployedDao.getOne(languageDto.getUnemployedId()));

		this.languageDao.save(language);
		return new SuccessResult("Dil eklendi.");
	}

	@Override
	public DataResult<List<Language>> getAll() {
		return new SuccessDataResult<List<Language>>(this.languageDao.findAll(), "Diller listelendi.");
	}

	@Override
	public DataResult<List<Language>> getByUnemployedId(int unemployedId) {
		return new SuccessDataResult<List<Language>>(this.languageDao.getByUnemployed_UserId(unemployedId),
				"İş arayanın dil bilgileri listelendi.");
	}

	@Override
	public Result deleteLanguage(int languageId) {
		if (!this.languageDao.existsById(languageId)) {
			return new ErrorResult("Dil bilgisi bulunamadı.");
		}
		this.languageDao.deleteById(languageId);
		return new SuccessResult("Dil bilgisi silindi.");
	}

	@Override
	public Result updateLanguage(LanguageDto languageDto, int languageId) {
		Language language = this.languageDao.getOne(languageId);
		if (language.getLanguageLevel() == languageDto.getLanguageLevel()
				&& language.getLanguageName() == languageDto.getLanguageName()) {
			return new ErrorResult("Değişiklik yapmadınız.");
		}
		language.setLanguageName(languageDto.getLanguageName());
		language.setLanguageLevel(languageDto.getLanguageLevel());
		this.languageDao.save(language);
		return new SuccessResult("Dil bilgisi başarıyla güncellendi.");
	}

}
