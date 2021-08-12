package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.EmployerUpdate;

public interface EmployerUpdateDao extends JpaRepository<EmployerUpdate, Integer>{

	EmployerUpdate getByEmployerIdAndApproveStatusFalse(int employerId);
	List<EmployerUpdate> getByApproveStatusFalse();
}
