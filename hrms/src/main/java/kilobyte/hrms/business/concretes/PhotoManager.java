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
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.PhotoDao;
import kilobyte.hrms.entities.concretes.Photo;
import kilobyte.hrms.entities.concretes.Unemployed;

@Service
public class PhotoManager implements PhotoService{

	private PhotoDao photoDao;
	private PhotoUploadService photoUploadService;
	
	@Autowired
	public PhotoManager(PhotoDao photoDao, PhotoUploadService photoUploadService) {
		super();
		this.photoUploadService = photoUploadService;
		this.photoDao = photoDao;
	}

	@Override
	public Result addPhoto(int unemployedId, MultipartFile multipartFile) throws IOException {
		var result = this.photoUploadService.upload(multipartFile);
		Unemployed unemployed = new Unemployed();
		Photo photo = new Photo();
		unemployed.setId(unemployedId);
		photo.setUnemployed(unemployed);
		photo.setPhotoUrl(result.getData().get("url").toString());
		photo.setUploadDate(LocalDate.now());
		this.photoDao.save(photo);		
		return new SuccessResult("Fotoğraf yüklendi.");
	}

	@Override
	public DataResult<List<Photo>> getAll() {
		return new SuccessDataResult<List<Photo>>(this.photoDao.findAll(),"Fotoğraflar listelendi.");
	}

	@Override
	public DataResult<Photo> getByUnemployedId(int unemployedId) {
		return new SuccessDataResult<Photo>(this.photoDao.getByUnemployedId(unemployedId));
	}


}
