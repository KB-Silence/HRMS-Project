package kilobyte.hrms.business.abstracts;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.User;

public interface UserService {
	
	DataResult<User> checkEmail(String email);
}
