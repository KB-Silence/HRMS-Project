package kilobyte.hrms.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kilobyte.hrms.business.abstracts.PhotoService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;

@RestController
@RequestMapping("/api/photos/")
@CrossOrigin
public class PhotosController {

	private PhotoService photoService;
	
	@Autowired
	public PhotosController(PhotoService photoService) {
		super();
		this.photoService = photoService;
	}
	
	@PostMapping("uploadPhoto")
	public ResponseEntity<?> uploadPhoto(@RequestParam int unemployedId, MultipartFile multipartFile) throws IOException {
		return ResponseEntityReturn.checkResult(this.photoService.uploadPhoto(unemployedId, multipartFile));
	}
	
	@DeleteMapping("deletePhoto")
	public ResponseEntity<?> deletePhoto(@RequestParam int unemployedId) {
		return ResponseEntityReturn.checkResult(this.photoService.deletePhoto(unemployedId));
	}
	
	@GetMapping("getAllPhotos")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.photoService.getAll());
	}
	
	@GetMapping("getByUnemployedId")
	public ResponseEntity<?> getByUnemployedId(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.photoService.getByUnemployedId(unemployedId));
	}
}
