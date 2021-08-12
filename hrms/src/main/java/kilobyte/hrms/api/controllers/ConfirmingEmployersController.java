package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.ConfirmingEmployerService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.concretes.ConfirmingEmployer;
import kilobyte.hrms.entities.concretes.Employer;

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
	
	@PostMapping("confirmEmployers")
	public ResponseEntity<?> confirmEmployers(@RequestParam int employeeId, int employerId) {
		return ResponseEntityReturn.checkResult(this.confirmingEmployerService.verifyEmployer(employeeId, employerId));
	}
	
	@GetMapping("getAllApprovedEmployers")
	public DataResult<List<ConfirmingEmployer>> getAll() {
		return this.confirmingEmployerService.getAll();
	}
	
	@GetMapping("getByEmployerIsConfirmed")
	public DataResult<List<Employer>> getByEmployerIsConfirmed() {
		return this.confirmingEmployerService.getByEmployerIsConfirmedFalse();
	}
}
