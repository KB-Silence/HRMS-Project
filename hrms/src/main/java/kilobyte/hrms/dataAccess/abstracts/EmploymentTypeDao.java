package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.EmploymentType;

public interface EmploymentTypeDao extends JpaRepository<EmploymentType, Integer> {

}
