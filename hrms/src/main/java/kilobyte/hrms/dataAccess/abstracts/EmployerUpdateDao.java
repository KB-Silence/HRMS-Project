package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.EmployerUpdate;

public interface EmployerUpdateDao extends JpaRepository<EmployerUpdate, Integer>{

	List<EmployerUpdate> getByApproveStatus(boolean status);
}
