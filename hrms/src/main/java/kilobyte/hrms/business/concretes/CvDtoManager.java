package kilobyte.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.CoverLetterService;
import kilobyte.hrms.business.abstracts.CvDtoService;
import kilobyte.hrms.business.abstracts.EducationService;
import kilobyte.hrms.business.abstracts.JobExperienceService;
import kilobyte.hrms.business.abstracts.LanguageService;
import kilobyte.hrms.business.abstracts.LinkService;
import kilobyte.hrms.business.abstracts.PhotoService;
import kilobyte.hrms.business.abstracts.TechnologyService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.dtos.CvDto;

@Service
public class CvDtoManager implements CvDtoService{

	private UnemployedDao unemployedDao;
	private EducationService educationService;
	private JobExperienceService jobExperienceService;
	private TechnologyService technologyService;
	private LanguageService languageService;
	private LinkService linkService;
	private CoverLetterService coverLetterService;
	private PhotoService photoService;
	
	@Autowired
	public CvDtoManager(UnemployedDao unemployedDao, EducationService educationService,
			JobExperienceService jobExperienceService, TechnologyService technologyService,
			LanguageService languageService, LinkService linkService, CoverLetterService coverLetterService,
			PhotoService photoService) {
		super();
		this.unemployedDao = unemployedDao;
		this.educationService = educationService;
		this.jobExperienceService = jobExperienceService;
		this.technologyService = technologyService;
		this.languageService = languageService;
		this.linkService = linkService;
		this.coverLetterService = coverLetterService;
		this.photoService = photoService;
	}

	@Override
	public DataResult<CvDto> createCv(int unemployedId) {
		CvDto cv = new CvDto();
		cv.setUnemployed(this.unemployedDao.findById(unemployedId).get());
		cv.setEducations(this.educationService.getByUnemployedIdOrderByGraduatedDateDesc(unemployedId).getData());
		cv.setJobExperiences(this.jobExperienceService.getByUnemployedIdOrderByLeaveDateDesc(unemployedId).getData());
		cv.setTechnologies(this.technologyService.getByUnemployedId(unemployedId).getData());
		cv.setLanguages(this.languageService.getByUnemployedId(unemployedId).getData());
		cv.setLink(this.linkService.getByUnemployedId(unemployedId).getData());
		cv.setCoverLetter(this.coverLetterService.getByUnemployedId(unemployedId).getData());
		cv.setPhoto(this.photoService.getByUnemployedId(unemployedId).getData());
		return new SuccessDataResult<CvDto>(cv);
	}

}
