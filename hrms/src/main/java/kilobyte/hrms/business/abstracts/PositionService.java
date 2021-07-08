package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Position;

public interface PositionService {
	
	Result addPosition(Position position);
	DataResult<List<Position>> getAll();
	DataResult<Position> getByPositionName(String positionName);
}
