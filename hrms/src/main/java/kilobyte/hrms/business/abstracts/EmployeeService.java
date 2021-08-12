package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Employee;
import kilobyte.hrms.entities.dtos.EmployeeDto;

public interface EmployeeService {

	Result addEmployee(EmployeeDto employeeDto);
	Result updateEmployee(EmployeeDto employeeDto, int employeeId);
	Result deleteEmployee(int employeeId);
	DataResult<Employee> getByUserId(int userId);
	DataResult<List<Employee>> getAll();
}
