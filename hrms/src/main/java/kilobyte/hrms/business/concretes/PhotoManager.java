package kilobyte.hrms.business.concretes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kilobyte.hrms.business.abstracts.PhotoService;
import kilobyte.hrms.core.abstracts.PhotoUploadService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorDataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.PhotoDao;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.Photo;

@Service
public class PhotoManager implements PhotoService {

	private PhotoDao photoDao;
	private PhotoUploadService photoUploadService;
	private UnemployedDao unemployedDao;

	private String defaultPhoto = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Breezeicons-actions-22-im-user.svg/512px-Breezeicons-actions-22-im-user.svg.png";

	@Autowired
	public PhotoManager(PhotoDao photoDao, PhotoUploadService photoUploadService, UnemployedDao unemployedDao) {
		super();
		this.photoUploadService = photoUploadService;
		this.photoDao = photoDao;
		this.unemployedDao = unemployedDao;
	}

	@Override
	public Result uploadPhoto(int unemployedId, MultipartFile file) throws IOException {
		Photo photo = this.photoDao.getByUnemployed_UserId(unemployedId);
		var result = this.photoUploadService.upload(file);
		photo.setPhotoUrl(result.getData().get("url").toString());
		photo.setUploadDate(LocalDate.now());
		this.photoDao.save(photo);
		return new SuccessResult("Fotoğraf yüklendi.");
	}

	@Override
	public Result newRegister(int unemployedId) {
		Photo photo = new Photo();
		photo.setPhotoUrl(defaultPhoto);
		photo.setUploadDate(LocalDate.now());
		photo.setUnemployed(this.unemployedDao.getOne(unemployedId));
		this.photoDao.save(photo);
		return new SuccessResult();
	}

	@Override
	public Result deletePhoto(int unemployedId) {
		try {
			Photo photo = this.photoDao.getByUnemployed_UserId(unemployedId);
			this.photoUploadService.delete(photo.getPhotoId());
			photo.setPhotoUrl(defaultPhoto);
			this.photoDao.save(photo);
			return new SuccessResult("Fotoğraf başarıyla silindi.");
		} catch (IOException exception) {
			return new ErrorResult("Fotoğraf silinemedi.");
		}
	}

	@Override
	public DataResult<List<Photo>> getAll() {
		return new SuccessDataResult<List<Photo>>(this.photoDao.findAll(), "Fotoğraflar listelendi.");
	}

	@Override
	public DataResult<Photo> getByUnemployedId(int unemployedId) {
		if(this.photoDao.getByUnemployed_UserId(unemployedId) == null) {
			return new ErrorDataResult<Photo>("Girdiğiniz kullanıcıya ait fotoğraf bulunamadı.");
		}
		return new SuccessDataResult<Photo>(this.photoDao.getByUnemployed_UserId(unemployedId));
	}

}
