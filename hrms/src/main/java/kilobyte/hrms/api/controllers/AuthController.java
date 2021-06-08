package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.AuthService;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Employer;
import kilobyte.hrms.entities.concretes.Unemployed;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	
	private AuthService authService;
	
	@Autowired
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/registerEmployer")
	public Result add(@RequestBody Employer employer, String confirmPassword) {
		return this.authService.registerEmployer(employer, confirmPassword);
	}
	
	@PostMapping("/registerUnemployed")
	public Result add(@RequestBody Unemployed unemployed, String confirmPassword) {
		return this.authService.registerUnemployed(unemployed, confirmPassword);
	}
	
}
