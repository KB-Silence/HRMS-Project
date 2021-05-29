package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.UnemployedService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.Unemployed;

@RestController
@RequestMapping("/api/unemployeds")
public class UnemployedsController {

	private UnemployedService unemployedService;
	
	@Autowired
	public UnemployedsController(UnemployedService unemployedService) {
		super();
		this.unemployedService = unemployedService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Unemployed>> getAll() {
		return this.unemployedService.getAll();
	}
	
}
