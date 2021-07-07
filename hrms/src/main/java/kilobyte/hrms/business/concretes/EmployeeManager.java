package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.EmployeeService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.dataAccess.abstracts.EmployeeDao;
import kilobyte.hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService{

	private EmployeeDao employeeDao;
	
	public EmployeeManager(EmployeeDao employeeDao) {
		super();
		this.employeeDao = employeeDao;
	}
	
	@Override
	public Result addEmployee(Employee employee) {
		return null;
		// Eklenecek.
		// AuthManager sınıfına aktarılabilir.
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Sistem personelleri listelendi.");
	}

}
