package kilobyte.hrms.business.abstracts;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Employer;
import kilobyte.hrms.entities.concretes.Unemployed;
import kilobyte.hrms.entities.concretes.User;
import kilobyte.hrms.entities.dtos.LoginDto;
import kilobyte.hrms.entities.dtos.LoginReturnDto;

public interface AuthService {
	
	DataResult<Unemployed> registerUnemployed(Unemployed unemployed, String confirmPassword);
	DataResult<Employer> registerEmployer(Employer employer, String confirmPassword);
	
	DataResult<LoginReturnDto> login(LoginDto loginDto);
	
	Result confirmPassword(String password, String confirmPassword);
	DataResult<User> checkEmail(String email);

}
