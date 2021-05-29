package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.verifications.Verification;

public interface VerificationDao extends JpaRepository<Verification, Integer>{

}
