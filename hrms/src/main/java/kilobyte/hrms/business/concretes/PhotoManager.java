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
	public Result addPhoto(int unemployedId, MultipartFile file) throws IOException {
		Photo forAdd = new Photo();
		var result = this.photoUploadService.upload(file);
		forAdd.setUnemployed(this.unemployedDao.getOne(unemployedId));
		forAdd.setPhotoUrl(result.getData().get("url").toString());
		forAdd.setUploadDate(LocalDate.now());
		this.photoDao.save(forAdd);
		return new SuccessResult("Fotoğraf yüklendi.");
	}

	@Override
	public Result updatePhoto(int unemployedId, MultipartFile file) throws IOException {
		Photo forUpdate = this.photoDao.getByUnemployed_UserId(unemployedId);
		if (forUpdate.getPhotoUrl() != null) {
			this.photoUploadService.delete(forUpdate.getPhotoId());
			var result = this.photoUploadService.upload(file);
			forUpdate.setPhotoUrl(result.getData().get("url").toString());
			forUpdate.setUploadDate(LocalDate.now());
			this.photoDao.save(forUpdate);
			return new SuccessResult("Fotoğraf başarıyla güncellendi.");
		} else {
			return new ErrorResult("Fotoğraf güncellenemedi.");
		}

	}

	@Override
	public Result deletePhoto(int photoId) {

		if (!this.photoDao.existsById(photoId)) {
			return new ErrorResult("Silinecek bir fotoğraf yok :( ...");
		}

		try {
			Photo photo = this.photoDao.getOne(photoId);
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
		return new SuccessDataResult<Photo>(this.photoDao.getByUnemployed_UserId(unemployedId));
	}

}
