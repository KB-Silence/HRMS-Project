package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.ConfirmingEmployerService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.ConfirmingEmployerDao;
import kilobyte.hrms.dataAccess.abstracts.EmployeeDao;
import kilobyte.hrms.dataAccess.abstracts.EmployerDao;
import kilobyte.hrms.entities.concretes.ConfirmingEmployer;
import kilobyte.hrms.entities.concretes.Employer;

@Service
public class ConfirmingEmployerManager implements ConfirmingEmployerService {

	private EmployeeDao employeeDao;
	private ConfirmingEmployerDao confirmingEmployerDao;
	private EmployerDao employerDao;

	@Autowired
	public ConfirmingEmployerManager(EmployeeDao employeeDao, ConfirmingEmployerDao confirmingEmployerDao,
			EmployerDao employerDao) {
		super();
		this.employeeDao = employeeDao;
		this.confirmingEmployerDao = confirmingEmployerDao;
		this.employerDao = employerDao;
	}

	@Override
	public Result verifyEmployer(int employeeId, int employerId) {

		Employer employer = this.employerDao.getOne(employerId);
		ConfirmingEmployer confirmEmployer = new ConfirmingEmployer();
		
		confirmEmployer.setEmployee(this.employeeDao.getOne(employeeId));
		confirmEmployer.setEmployer(this.employerDao.getOne(employerId));
		confirmEmployer.setVerifiedStatus(true);
		
		employer.setEmployerIsConfirmed(true);
		
		this.employerDao.save(employer);
		this.confirmingEmployerDao.save(confirmEmployer);

		return new SuccessResult("İş veren onaylandı.");
	}

	@Override
	public DataResult<List<ConfirmingEmployer>> getAll() {
		return new SuccessDataResult<List<ConfirmingEmployer>>(this.confirmingEmployerDao.findAll());
	}

	@Override
	public DataResult<List<Employer>> getByEmployerIsConfirmedFalse() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getByEmployerIsConfirmedFalse(), "Onaylanmayan iş verenler listelendi.");
	}

}
