package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.PositionService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.PositionDao;
import kilobyte.hrms.entities.concretes.Position;

@Service
public class PositionManager implements PositionService {

	private PositionDao positionDao;

	@Autowired
	public PositionManager(PositionDao positionDao) {
		super();
		this.positionDao = positionDao;
	}

	private DataResult<Position> positionIsExist(String positionName) {
		return new SuccessDataResult<Position>(this.positionDao.findByPositionName(positionName));
	}

	@Override
	public Result add(Position position) {
		if (this.positionIsExist(position.getPositionName()).getData() != null) {
			return new ErrorResult("Pozisyon mevcut.");
		}

		this.positionDao.save(position);
		return new SuccessResult("Pozisyon başarıyla eklendi.");
	}

	@Override
	public DataResult<List<Position>> getAll() {
		return new SuccessDataResult<List<Position>>(this.positionDao.findAll(), "Mevcut pozisyonlar listelendi.");
	}

	@Override
	public DataResult<Position> getByPositionName(String positionName) {
		return new SuccessDataResult<Position>(this.positionDao.findByPositionName(positionName));
	}
}
