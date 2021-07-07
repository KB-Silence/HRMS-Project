package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.EducationService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.EducationDao;
import kilobyte.hrms.entities.concretes.Education;

@Service
public class EducationManager implements EducationService{

	private EducationDao educationDao;
	
	public EducationManager(EducationDao educationDao) {
		super();
		this.educationDao = educationDao;
	}
	
	@Override
	public Result addEducation(Education education) {
		this.educationDao.save(education);
		return new SuccessResult("Eğitim bilgileri eklendi.");
	}

	@Override
	public DataResult<List<Education>> getAll() {
		return new SuccessDataResult<List<Education>>(this.educationDao.findAll(),"Eğitim bilgileri listelendi.");
	}

	@Override
	public DataResult<List<Education>> getByUnemployedIdOrderByGraduatedDateDesc(int unemployedId) {
		return new SuccessDataResult<List<Education>>(this.educationDao.getByUnemployedIdOrderByGraduatedDateDesc(unemployedId),"Devam ediyor.");
	}

	@Override
	public Result deleteEducation(int educationId) {
		if(!this.educationDao.existsById(educationId)) {
			return new ErrorResult("Okul bilgisi bulunamadı.");
		}
		this.educationDao.deleteById(educationId);
		return new SuccessResult("Okul bilgisi silindi.");
	}

}
