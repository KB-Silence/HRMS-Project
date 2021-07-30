package kilobyte.hrms.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.EducationService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.dtos.EducationDto;

@RestController
@RequestMapping("/api/educations/")
@CrossOrigin
public class EducationsController {

	private EducationService educationService;
	
	public EducationsController(EducationService educationService) {
		super();
		this.educationService = educationService;
	}
	
	@PostMapping("addEducations")
	public ResponseEntity<?> addEducation(@RequestBody EducationDto educationDto) {
		return ResponseEntityReturn.checkResult(this.educationService.addEducation(educationDto));
	}
	
	@DeleteMapping("deleteEducations")
	public ResponseEntity<?> deleteEducation(@RequestParam int educationId) {
		return ResponseEntityReturn.checkResult(this.educationService.deleteEducation(educationId));
	}
	
	@GetMapping("getAllEducations")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.educationService.getAll());
	}
	
	@GetMapping("getByUnemployedIdOrderByGraduatedDate")
	public ResponseEntity<?> getByUnemployedIdOrderByGraduatedDateDesc(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.educationService.getByUnemployedIdOrderByGraduatedDateDesc(unemployedId));
	}
}
