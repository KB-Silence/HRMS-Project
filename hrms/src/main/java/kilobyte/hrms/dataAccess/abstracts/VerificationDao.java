package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Verification;

public interface VerificationDao extends JpaRepository<Verification, Integer>{
	
	Verification findByVerificationCode(String verificationCode);
}
