package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.JobAdvertFavorite;

public interface JobAdvertFavoriteService {

	Result addFavorite(int unemployedId, int advertId);
	Result deleteFavorite(int favoriteId);
	DataResult<List<JobAdvertFavorite>> getByUnemployedId(int unemployedId);
}
