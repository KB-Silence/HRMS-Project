package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.EmploymentTime;

public interface EmploymentTimeDao extends JpaRepository<EmploymentTime, Integer> {

}
