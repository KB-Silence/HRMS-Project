package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{
	User findByEmail(String email);
	User getById(int userId);
	List<User> findByMailIsVerifyTrue();
}
