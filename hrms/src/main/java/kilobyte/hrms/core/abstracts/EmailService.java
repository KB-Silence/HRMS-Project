package kilobyte.hrms.core.abstracts;

import kilobyte.hrms.entities.concretes.User;

public interface EmailService {
	
	void sendVerifyEmail(User user, String code);

}
