package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.LanguageService;
import kilobyte.hrms.entities.dtos.LanguageDto;

@RestController
@RequestMapping("/api/languages/")
@CrossOrigin
public class LanguagesController {

	private LanguageService languageService;
	
	@Autowired
	public LanguagesController(LanguageService languageService) {
		super();
		this.languageService = languageService;
	}
	
	@PostMapping("addLanguage")
	public ResponseEntity<?> addLanguage(@RequestBody LanguageDto languageDto) {
		return ResponseEntity.ok(this.languageService.addLanguage(languageDto));
	}
	
	@DeleteMapping("deleteLanguage")
	public ResponseEntity<?> deleteLanguage(@RequestParam int languageId) {
		return ResponseEntity.ok(this.languageService.deleteLanguage(languageId));
	}
	
	@GetMapping("getAllLanguages")
	public ResponseEntity<?> gettAll() {
		return ResponseEntity.ok(this.languageService.getAll());
	}
	
	@GetMapping("getByUnemployedId")
	public ResponseEntity<?> getByUnemployedId(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.languageService.getByUnemployedId(unemployedId));
	}
}
