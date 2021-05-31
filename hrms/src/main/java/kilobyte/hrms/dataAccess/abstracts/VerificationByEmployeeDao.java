package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.ConfirmationByEmployee;

public interface VerificationByEmployeeDao extends JpaRepository<ConfirmationByEmployee, Integer> {

}
