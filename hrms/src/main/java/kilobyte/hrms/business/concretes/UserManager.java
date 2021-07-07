package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.UserService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.dataAccess.abstracts.UserDao;
import kilobyte.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	
	@Override
	public DataResult<User> checkEmail(String email) {
		return new SuccessDataResult<User>(this.userDao.findByEmail(email));
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll(), "Bütün kullanıcılar listelendi.");
	}

	@Override
	public DataResult<List<User>> getByMailConfirmed() {
		return new SuccessDataResult<List<User>>(this.userDao.findByMailIsVerifyTrue(), "Onaylanmış kullanıcılar listelendi.");
	}

}
