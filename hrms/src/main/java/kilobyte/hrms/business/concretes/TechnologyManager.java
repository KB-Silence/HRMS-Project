package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.TechnologyService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.TechnologyDao;
import kilobyte.hrms.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService{

	private TechnologyDao technologyDao;
	
	public TechnologyManager(TechnologyDao technologyDao) {
		super();
		this.technologyDao = technologyDao;
	}
	
	@Override
	public Result addTechnology(Technology technology) {
		this.technologyDao.save(technology);
		return new SuccessResult("Teknoloji bilgisi eklendi.");
	}

	@Override
	public DataResult<List<Technology>> getAll() {
		return new SuccessDataResult<List<Technology>>(this.technologyDao.findAll(),"Teknoloji bilgileri listelendi.");
	}

	@Override
	public DataResult<List<Technology>> getByUnemployedId(int unemployedId) {
		return new SuccessDataResult<List<Technology>>(this.technologyDao.getByUnemployedId(unemployedId),"İş arayanın teknoloji bilgileri listelendi.");
	}

}
