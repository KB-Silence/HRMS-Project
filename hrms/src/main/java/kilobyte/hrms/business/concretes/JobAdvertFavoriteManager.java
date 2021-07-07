package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.JobAdvertFavoriteService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorDataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.JobAdvertFavoriteDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.JobAdvertFavorite;

@Service
public class JobAdvertFavoriteManager implements JobAdvertFavoriteService {

	private JobAdvertFavoriteDao favoriteDao;
	private UnemployedDao unemployedDao;
	
	public JobAdvertFavoriteManager(JobAdvertFavoriteDao favoriteDao, UnemployedDao unemployedDao) {
		super();
		this.favoriteDao = favoriteDao;
	}
	
	@Override
	public Result addFavorite(int unemployedId, int advertId) {
		// TODO Auto-generated method stub
		return null;
		// Eklenecek...
	}

	@Override
	public Result deleteFavorite(int favoriteId) {
		if(!this.favoriteDao.existsById(favoriteId)) {
			return new ErrorResult("Silmek istediğin ilan favorilerinde bulunamadı.");
		}
		this.favoriteDao.deleteById(favoriteId);
		return new SuccessResult("İlan başarıyla favorilerden kaldırıldı.");
	}

	@Override
	public DataResult<List<JobAdvertFavorite>> getByUnemployedId(int unemployedId) {
		if(!this.unemployedDao.existsById(unemployedId)) {
			return new ErrorDataResult<List<JobAdvertFavorite>>("Girdiğiniz kullanıcı bulunamadı.");
		}
		return new SuccessDataResult<List<JobAdvertFavorite>>(this.favoriteDao.findByUnemployedId(unemployedId), "Kullanıcının favori ilanları listelendi.");
	}

}
