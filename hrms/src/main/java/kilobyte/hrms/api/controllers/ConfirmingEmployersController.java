package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.ConfirmingEmployerService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.ConfirmingEmployer;

@RestController
@RequestMapping("/api/confirmingEmployer/")
@CrossOrigin
public class ConfirmingEmployersController {

	private ConfirmingEmployerService confirmingEmployerService; 
	
	@Autowired
	public ConfirmingEmployersController(ConfirmingEmployerService confirmingEmployerService) {
		super();
		this.confirmingEmployerService = confirmingEmployerService;
	}
	
	@PostMapping("add")
	public Result add(@RequestBody ConfirmingEmployer confiringEmployer) {
		return this.confirmingEmployerService.verify(confiringEmployer);
	}
	
	@GetMapping("getAllEmployers")
	public DataResult<List<ConfirmingEmployer>> getAll() {
		return this.confirmingEmployerService.getAll();
	}
}
