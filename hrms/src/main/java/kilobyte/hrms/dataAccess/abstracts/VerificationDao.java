package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Verification;

public interface VerificationDao extends JpaRepository<Verification, Integer>{
	
	Verification getByVerificationCode(String verificationCode);
	Verification getByUserId(int userId);
}
