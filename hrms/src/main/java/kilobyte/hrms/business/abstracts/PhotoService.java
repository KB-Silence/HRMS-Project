package kilobyte.hrms.business.abstracts;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Photo;

public interface PhotoService {
	
	Result uploadPhoto(int unemployedId, MultipartFile file) throws IOException;
	Result newRegister(int unemployedId);
	Result deletePhoto(int unemployedId);
	DataResult<List<Photo>> getAll();
	DataResult<Photo> getByUnemployedId(int unemployedId);
	
}
