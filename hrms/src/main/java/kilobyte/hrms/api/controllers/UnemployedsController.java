package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.CvDtoService;
import kilobyte.hrms.business.abstracts.UnemployedService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.Unemployed;

@RestController
@RequestMapping("/api/unemployeds")
@CrossOrigin
public class UnemployedsController {

	private UnemployedService unemployedService;
	private CvDtoService cvDtoService;
	
	@Autowired
	public UnemployedsController(UnemployedService unemployedService, CvDtoService cvDtoService) {
		super();
		this.unemployedService = unemployedService;
		this.cvDtoService = cvDtoService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Unemployed>> getAll() {
		return this.unemployedService.getAll();
	}
	
	@GetMapping("createCv")
	public ResponseEntity<?> createCv(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.cvDtoService.createCv(unemployedId));
	}
	
}
