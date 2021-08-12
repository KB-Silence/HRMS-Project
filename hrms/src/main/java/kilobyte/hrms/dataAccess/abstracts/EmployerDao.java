package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	Employer getByEmail(String email);
	Employer getByUserId(int userId);
	List<Employer> getByMailIsVerifyTrue();
	List<Employer> getByEmployerIsConfirmedFalse();
	List<Employer> getByWaitingForUpdateTrue();

}
