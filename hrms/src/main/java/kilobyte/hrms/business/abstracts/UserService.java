package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.User;

public interface UserService {
	
	DataResult<List<User>> getAll();
	DataResult<List<User>> getByMailConfirmed();
	DataResult<User> checkEmail(String email);
	
}
