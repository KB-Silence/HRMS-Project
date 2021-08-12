package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.CoverLetterService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.CoverLetterDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.CoverLetter;
import kilobyte.hrms.entities.dtos.CoverLetterDto;

@Service
public class CoverLetterManager implements CoverLetterService {

	private CoverLetterDao coverLetterDao;
	private UnemployedDao unemployedDao;

	@Autowired
	public CoverLetterManager(CoverLetterDao coverLetterDao, UnemployedDao unemployedDao) {
		super();
		this.coverLetterDao = coverLetterDao;
		this.unemployedDao = unemployedDao;
	}

	@Override
	public Result addCoverLetter(CoverLetterDto coverLetterDto) {
		if (this.coverLetterDao.getByUnemployed_UserId(coverLetterDto.getUnemployedId()) != null) {
			return new ErrorResult("Zaten ön yazı eklemişsiniz.");
		}
		CoverLetter coverLetter = new CoverLetter();
		coverLetter.setLetterContent(coverLetterDto.getLetterContent());
		coverLetter.setUnemployed(this.unemployedDao.getOne(coverLetterDto.getUnemployedId()));

		this.coverLetterDao.save(coverLetter);
		return new SuccessResult("Ön yazı eklendi.");
	}

	@Override
	public DataResult<List<CoverLetter>> getAll() {
		return new SuccessDataResult<List<CoverLetter>>(this.coverLetterDao.findAll(), "Ön yazılar listelendi.");
	}

	@Override
	public DataResult<CoverLetter> getByUnemployedId(int unemployedId) {
		return new SuccessDataResult<CoverLetter>(this.coverLetterDao.getByUnemployed_UserId(unemployedId),
				"İş arayanın ön yazısı listelendi.");
	}

	@Override
	public Result updateCoverLetter(CoverLetterDto coverLetterDto) {
		if (this.coverLetterDao.getByUnemployed_UserId(coverLetterDto.getUnemployedId()) == null) {
			return new ErrorResult("Önce ön yazı eklemelisiniz.");
		}
		CoverLetter coverLetter = this.coverLetterDao.getByUnemployed_UserId(coverLetterDto.getUnemployedId());
		if (coverLetter.getLetterContent().equals(coverLetterDto.getLetterContent())) {
			return new ErrorResult("Güncelleme yapmayı unuttun :)");
		}
		coverLetter.setLetterContent(coverLetterDto.getLetterContent());
		this.coverLetterDao.save(coverLetter);
		return new SuccessResult("Ön yazı güncellendi.");
	}

}
