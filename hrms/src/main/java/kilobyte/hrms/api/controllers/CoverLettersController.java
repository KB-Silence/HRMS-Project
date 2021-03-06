package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.CoverLetterService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.dtos.CoverLetterDto;

@RestController
@RequestMapping("/api/coverletters/")
@CrossOrigin
public class CoverLettersController {

	private CoverLetterService coverLetterService;
	
	@Autowired
	public CoverLettersController(CoverLetterService coverLetterService) {
		super();
		this.coverLetterService = coverLetterService;
	}
	
	@PostMapping("addCoverLetter")
	public ResponseEntity<?> addCoverLetter(@RequestBody CoverLetterDto coverLetterDto) {
		return ResponseEntityReturn.checkResult(this.coverLetterService.addCoverLetter(coverLetterDto));
	}
	
	@PutMapping("updateCoverLetter")
	public ResponseEntity<?> updateCoverLetter(@RequestBody CoverLetterDto coverLetterDto) {
		return ResponseEntityReturn.checkResult(this.coverLetterService.updateCoverLetter(coverLetterDto));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.coverLetterService.getAll());
	}
	
	@GetMapping("getByUnemployedId")
	public ResponseEntity<?> getByUnemployedId(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.coverLetterService.getByUnemployedId(unemployedId));
	}
}
