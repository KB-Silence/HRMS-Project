package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Employee;
import kilobyte.hrms.entities.dtos.EmployeeUpdateDto;

public interface EmployeeService {

	Result addEmployee(Employee employee);
	Result updateEmployee(EmployeeUpdateDto employeeDto);
	DataResult<List<Employee>> getAll();
}
