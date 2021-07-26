package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.JobAdvertFavoriteService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorDataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.JobAdvertFavoriteDao;
import kilobyte.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.JobAdvertFavorite;

@Service
public class JobAdvertFavoriteManager implements JobAdvertFavoriteService {

	private JobAdvertisementDao advertisementDao;
	private JobAdvertFavoriteDao favoriteDao;
	private UnemployedDao unemployedDao;
	
	@Autowired
	public JobAdvertFavoriteManager(JobAdvertFavoriteDao favoriteDao, UnemployedDao unemployedDao, JobAdvertisementDao advertisementDao) {
		super();
		this.favoriteDao = favoriteDao;
		this.unemployedDao = unemployedDao;
		this.advertisementDao = advertisementDao;
	}
	
	@Override
	public Result addFavorite(int unemployedId, int advertId) {
		
		if(!this.unemployedDao.existsById(unemployedId)) {
			return new ErrorResult("Kullanıcı bulunamadı.");
		}else if (!this.advertisementDao.existsById(advertId)) {
			return new ErrorResult("İlan bulunamadı.");
		}else if(!this.favoriteDao.existsByUnemployedIdAndJobAdvertisement_AdvertId(unemployedId, advertId)) {
			return new ErrorResult("İlan zaten favorilere eklenmiş.");
		}
		
		JobAdvertFavorite advertFavorite = new JobAdvertFavorite();
		advertFavorite.setUnemployed(this.unemployedDao.getOne(unemployedId));
		advertFavorite.setJobAdvertisement(this.advertisementDao.getOne(advertId));
		this.favoriteDao.save(advertFavorite);
		return new SuccessResult("İlan favorilere eklendi.");
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
