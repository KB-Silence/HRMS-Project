package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.TechnologyService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.TechnologyDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.Technology;
import kilobyte.hrms.entities.dtos.TechnologyDto;

@Service
public class TechnologyManager implements TechnologyService {

	private TechnologyDao technologyDao;
	private UnemployedDao unemployedDao;

	public TechnologyManager(TechnologyDao technologyDao, UnemployedDao unemployedDao) {
		super();
		this.technologyDao = technologyDao;
		this.unemployedDao = unemployedDao;
	}

	@Override
	public Result addTechnology(TechnologyDto technologyDto) {
		Technology technology = new Technology();
		if (this.technologyDao.getByTechnologyNameAndUnemployed_UserId(technologyDto.getTechnologyName(),
				technologyDto.getUnemployedId()) != null) {
			return new ErrorResult("Bu teknolojiyi zaten eklemişsiniz.");
		}
		technology.setTechnologyName(technologyDto.getTechnologyName());
		technology.setTechnologyLevel(technologyDto.getTechnologyLevel());
		technology.setUnemployed(this.unemployedDao.getOne(technologyDto.getUnemployedId()));

		this.technologyDao.save(technology);
		return new SuccessResult("Teknoloji bilgisi eklendi.");
	}

	@Override
	public DataResult<List<Technology>> getAll() {
		return new SuccessDataResult<List<Technology>>(this.technologyDao.findAll(), "Teknoloji bilgileri listelendi.");
	}

	@Override
	public DataResult<List<Technology>> getByUnemployedId(int unemployedId) {
		return new SuccessDataResult<List<Technology>>(this.technologyDao.getByUnemployed_UserId(unemployedId),
				"İş arayanın teknoloji bilgileri listelendi.");
	}

	@Override
	public Result deleteTechnology(int technologyId) {
		if (!this.technologyDao.existsById(technologyId)) {
			return new ErrorResult("Teknoloji bilgisi bulunamadı.");
		}
		this.technologyDao.deleteById(technologyId);
		return new SuccessResult("Teknoloji bilgisi silindi.");
	}

	@Override
	public Result updateTechnology(TechnologyDto technologyDto, int technologyId) {
		Technology technology = this.technologyDao.getOne(technologyId);
		if (technology.getTechnologyLevel() == technologyDto.getTechnologyLevel()
				&& technology.getTechnologyName() == technologyDto.getTechnologyName()) {
			return new ErrorResult("Değişiklik yapmadınız.");
		}
		technology.setTechnologyName(technologyDto.getTechnologyName());
		technology.setTechnologyLevel(technologyDto.getTechnologyLevel());
		this.technologyDao.save(technology);
		return new SuccessResult("Teknoloji bilgisi başarıyla güncellendi.");
	}

}
