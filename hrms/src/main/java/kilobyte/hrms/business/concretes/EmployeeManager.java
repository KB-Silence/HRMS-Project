package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.EmployeeService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.EmployeeDao;
import kilobyte.hrms.dataAccess.abstracts.UserDao;
import kilobyte.hrms.entities.concretes.Employee;
import kilobyte.hrms.entities.dtos.EmployeeUpdateDto;

@Service
public class EmployeeManager implements EmployeeService{

	private EmployeeDao employeeDao;
	private UserDao userDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao, UserDao userDao) {
		super();
		this.employeeDao = employeeDao;
		this.userDao = userDao;
	}
	
	@Override
	public Result addEmployee(Employee employee) {
		if(this.userDao.findByEmail(employee.getEmail()) == null) {
			employee.setMailIsVerify(true);
			this.employeeDao.save(employee);
			return new SuccessResult("Personel eklendi.");
		}
		return new ErrorResult("Personel eklenmedi. Bilgileri kontrol edip tekrar deneyiniz.");
	}
	
	@Override
	public Result updateEmployee(EmployeeUpdateDto employeeDto) {
		if(this.employeeDao.existsById(employeeDto.getEmployeeId())) {
			Employee employee = this.employeeDao.getOne(employeeDto.getEmployeeId());
			employee.setFirstName(employeeDto.getFirstName());
			employee.setLastName(employeeDto.getLastName());
			employee.setEmail(employeeDto.getEmail());
			employee.setPhoneNumber(employeeDto.getPhoneNumber());
			employee.setPositionId(employeeDto.getPositionId());
			this.employeeDao.save(employee);
			return new SuccessResult("Personel bilgileri güncellendi.");
		}
		return new ErrorResult("Personel bilgileri güncellenemedi. Lütfen kontrol edip tekrar deneyin.");
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Sistem personelleri listelendi.");
	}



}
