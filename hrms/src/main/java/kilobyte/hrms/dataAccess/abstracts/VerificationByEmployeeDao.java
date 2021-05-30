package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.VerificationByEmployee;

public interface VerificationByEmployeeDao extends JpaRepository<VerificationByEmployee, Integer> {

}
