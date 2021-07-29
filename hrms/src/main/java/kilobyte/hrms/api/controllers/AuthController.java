package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.AuthService;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.dtos.EmployerRegisterDto;
import kilobyte.hrms.entities.dtos.UnemployedRegisterDto;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
public class AuthController {
	
	private AuthService authService;
	
	@Autowired
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("registerEmployer")
	public ResponseEntity<?> registerEmployer(@RequestBody EmployerRegisterDto employerDto, @RequestParam String confirmPassword) {
//		return this.authService.registerEmployer(employerDto, confirmPassword);
		Result result = this.authService.registerEmployer(employerDto, confirmPassword);
		if(result.isSuccess()) {
			return ResponseEntity.ok(result);
		}
		return ResponseEntity.badRequest().body(result);
	}
	
	@PostMapping("registerUnemployed")
	public Result add(@RequestBody UnemployedRegisterDto unemployedDto, String confirmPassword) {
		return this.authService.registerUnemployed(unemployedDto, confirmPassword);
	}
	
}
