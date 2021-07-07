package kilobyte.hrms.core.abstracts;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kilobyte.hrms.core.utilities.results.DataResult;

public interface PhotoUploadService {
	
	@SuppressWarnings("rawtypes")
	DataResult<Map> upload(MultipartFile multipartFile) throws IOException;
	
	@SuppressWarnings("rawtypes")
	DataResult<Map> delete(int photoId) throws IOException;
}
