package kilobyte.hrms.business.abstracts;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.dtos.EmployerRegisterDto;
import kilobyte.hrms.entities.dtos.LoginDto;
import kilobyte.hrms.entities.dtos.LoginReturnDto;
import kilobyte.hrms.entities.dtos.UnemployedRegisterDto;

public interface AuthService {
	
	DataResult<UnemployedRegisterDto> registerUnemployed(UnemployedRegisterDto unemployedDto, String confirmPassword);
	DataResult<EmployerRegisterDto> registerEmployer(EmployerRegisterDto employerDto, String confirmPassword);
	
	DataResult<LoginReturnDto> login(LoginDto loginDto);
	
	Result confirmPassword(String password, String confirmPassword);
	Result checkEmail(String email);

}
