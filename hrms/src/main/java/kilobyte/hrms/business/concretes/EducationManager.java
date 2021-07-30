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
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.Education;
import kilobyte.hrms.entities.dtos.EducationDto;

@Service
public class EducationManager implements EducationService{

	private EducationDao educationDao;
	private UnemployedDao unemployedDao;
	
	public EducationManager(EducationDao educationDao, UnemployedDao unemployedDao) {
		super();
		this.educationDao = educationDao;
		this.unemployedDao = unemployedDao;
	}
	
	@Override
	public Result addEducation(EducationDto educationDto) {
		
		Education education = new Education();
		education.setSchoolName(educationDto.getSchoolName());
		education.setDepartment(educationDto.getDepartment());
		education.setStartDate(educationDto.getStartDate());
		education.setGraduatedDate(educationDto.getGraduatedDate());
		education.setUnemployed(this.unemployedDao.getOne(educationDto.getUnemployedId()));
		
		this.educationDao.save(education);
		return new SuccessResult("Eğitim bilgileri eklendi.");
	}

	@Override
	public DataResult<List<Education>> getAll() {
		return new SuccessDataResult<List<Education>>(this.educationDao.findAll(),"Eğitim bilgileri listelendi.");
	}

	@Override
	public DataResult<List<Education>> getByUnemployedIdOrderByGraduatedDateDesc(int unemployedId) {
		return new SuccessDataResult<List<Education>>(this.educationDao.getByUnemployed_UserIdOrderByGraduatedDateDesc(unemployedId),"Devam ediyor.");
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
