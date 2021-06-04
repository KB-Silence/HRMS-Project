package kilobyte.hrms.core.adapters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kilobyte.hrms.core.abstracts.PhotoUploadService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;

@Service
public class PhotoUploadManager implements PhotoUploadService {
	
	private Cloudinary cloudinary;
	private Map<String, String> valuesMap = new HashMap<>();
	
	public PhotoUploadManager() {
		valuesMap.put("cloud_name", "hrmsproject");
		valuesMap.put("api_key", "242199341551429");
		valuesMap.put("api_secret", "mB9E2gaCgHlgmsvIReKCuAI9aFY");
		this.cloudinary = new Cloudinary(valuesMap);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public DataResult<Map> upload(MultipartFile multipartFile) throws IOException {
		File file = fileConvert(multipartFile);
		Map resultMap = this.cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		file.delete();
		return new SuccessDataResult<>(resultMap);
	}
	
	private File fileConvert(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(multipartFile.getBytes());
		fileOutputStream.close();
		return file;
	}

}
