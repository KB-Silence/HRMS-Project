package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	
	Employee getByUserId(int userId);
	Employee getByEmail(String email);
}
