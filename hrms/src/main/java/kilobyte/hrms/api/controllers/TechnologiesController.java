package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.TechnologyService;
import kilobyte.hrms.entities.concretes.Technology;

@RestController
@RequestMapping("/api/technologies/")
@CrossOrigin
public class TechnologiesController {

	private TechnologyService technologyService;
	
	@Autowired
	public TechnologiesController(TechnologyService technologyService) {
		super();
		this.technologyService = technologyService;
	}
	
	@PostMapping("addTechnology")
	public ResponseEntity<?> addTechnology(@RequestBody Technology technology) {
		return ResponseEntity.ok(this.technologyService.addTechnology(technology));
	}
	
	@GetMapping("getAllTechnologies")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.technologyService.getAll());
	}
	
	@GetMapping("getByUnemployedId")
	public ResponseEntity<?> getByUnemployedId(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.technologyService.getByUnemployedId(unemployedId));
	}
}
