package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.VerificationService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;

@RestController
@RequestMapping("/api/verification/")
@CrossOrigin
public class VerificationsController {

	private VerificationService verification;
	
	@Autowired
	public VerificationsController(VerificationService verification) {
		super();
		this.verification = verification;
	}
	
	@GetMapping("/approve/{code}")
	public ResponseEntity<?> verifyUser(@PathVariable String code) {
		return ResponseEntityReturn.checkResult(this.verification.verifyUser(code));
	}
	
	
}
