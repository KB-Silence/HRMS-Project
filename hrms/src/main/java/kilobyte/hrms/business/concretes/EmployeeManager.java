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
import kilobyte.hrms.dataAccess.abstracts.PositionDao;
import kilobyte.hrms.dataAccess.abstracts.UserDao;
import kilobyte.hrms.entities.concretes.Employee;
import kilobyte.hrms.entities.dtos.EmployeeDto;

@Service
public class EmployeeManager implements EmployeeService {

	private EmployeeDao employeeDao;
	private UserDao userDao;
	private PositionDao positionDao;

	@Autowired
	public EmployeeManager(EmployeeDao employeeDao, UserDao userDao, PositionDao positionDao) {
		super();
		this.employeeDao = employeeDao;
		this.userDao = userDao;
		this.positionDao = positionDao;
	}

	@Override
	public Result addEmployee(EmployeeDto employeeDto) {
		if (this.userDao.findByEmail(employeeDto.getEmail()) != null) {
			return new ErrorResult("Bu mail adresi daha önce alınmış.");
		}
		Employee employee = new Employee();
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setPassword(employeeDto.getPassword());
		employee.setPosition(this.positionDao.getOne(employeeDto.getPositionId()));
		employee.setMailIsVerify(true);
		this.employeeDao.save(employee);
		return new SuccessResult("Personel başarıyla eklendi.");
	}

	@Override
	public Result updateEmployee(EmployeeDto employeeDto, int employeeId) {
		Employee employee = this.employeeDao.getOne(employeeId);
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setPosition(this.positionDao.getOne(employeeDto.getPositionId()));
		employee.setPassword(employeeDto.getPassword());
		this.employeeDao.save(employee);
		return new SuccessResult("Personel bilgileri güncellendi.");
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Sistem personelleri listelendi.");
	}

	@Override
	public DataResult<Employee> getByUserId(int userId) {
		return new SuccessDataResult<Employee>(this.employeeDao.getByUserId(userId));
	}

	@Override
	public Result deleteEmployee(int employeeId) {
		this.employeeDao.deleteById(employeeId);
		return new SuccessResult("Personel Silindi.");
	}

}
