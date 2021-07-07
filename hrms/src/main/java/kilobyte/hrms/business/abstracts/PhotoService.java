package kilobyte.hrms.business.abstracts;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Photo;

public interface PhotoService {
	
	Result addPhoto(int unemployedId, MultipartFile file) throws IOException;
	Result deletePhoto(int photoId);
	DataResult<List<Photo>> getAll();
	DataResult<Photo> getByUnemployedId(int unemployedId);
	
}
