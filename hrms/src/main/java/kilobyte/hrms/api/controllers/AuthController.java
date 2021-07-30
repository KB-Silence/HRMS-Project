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
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.dtos.EmployerRegisterDto;
import kilobyte.hrms.entities.dtos.LoginDto;
import kilobyte.hrms.entities.dtos.LoginReturnDto;
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
		return ResponseEntityReturn.checkResult(this.authService.registerEmployer(employerDto, confirmPassword));
	}
	
	@PostMapping("registerUnemployed")
	public ResponseEntity<?> registerUnemployed(@RequestBody UnemployedRegisterDto unemployedDto, String confirmPassword) {
		return ResponseEntityReturn.checkResult(this.authService.registerUnemployed(unemployedDto, confirmPassword));
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		DataResult<LoginReturnDto> loginReturnDto = this.authService.login(loginDto);
		if(loginReturnDto.isSuccess()) {
			return ResponseEntity.ok(loginReturnDto);
		}
		return ResponseEntity.badRequest().body(loginReturnDto);
	}
	
}
