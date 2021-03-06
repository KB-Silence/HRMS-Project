package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.EmployerService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.concretes.EmployerUpdate;

@RestController
@RequestMapping("/api/employers/")
@CrossOrigin
public class EmployersController {

	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}

	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntityReturn.checkResult(this.employerService.getAll());
	}

	@GetMapping("getByMailIsVerifyTrue")
	public ResponseEntity<?> getByMailIsVerifyTrue() {
		return ResponseEntityReturn.checkResult(this.employerService.getByMailIsVerifyTrue());
	}

	@GetMapping("getByUserId")
	public ResponseEntity<?> getByUserId(@RequestParam int userId) {
		return ResponseEntityReturn.checkResult(this.employerService.getByUserId(userId));
	}

	@PutMapping("updateEmployer")
	public ResponseEntity<?> updateEmployer(@RequestBody EmployerUpdate employerUpdate) {
		return ResponseEntityReturn.checkResult(this.employerService.updateEmployer(employerUpdate));
	}
}
