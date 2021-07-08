package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.CoverLetterService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.CoverLetterDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.CoverLetter;
import kilobyte.hrms.entities.dtos.CoverLetterDto;

@Service
public class CoverLetterManager implements CoverLetterService{

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
		CoverLetter coverLetter = new CoverLetter();
		coverLetter.setLetterContent(coverLetterDto.getLetterContent());
		coverLetter.setUnemployed(this.unemployedDao.getOne(coverLetterDto.getUnemployedId()));
		
		this.coverLetterDao.save(coverLetter);
		return new SuccessResult("Ön yazı eklendi.");
	}

	@Override
	public DataResult<List<CoverLetter>> getAll() {
		return new SuccessDataResult<List<CoverLetter>>(this.coverLetterDao.findAll(),"Ön yazılar listelendi.");
	}

	@Override
	public DataResult<List<CoverLetter>> getByUnemployedId(int unemployedId) {
		return new SuccessDataResult<List<CoverLetter>>(this.coverLetterDao.getByUnemployedId(unemployedId),"İş arayanın ön yazısı listelendi.");
	}

}
