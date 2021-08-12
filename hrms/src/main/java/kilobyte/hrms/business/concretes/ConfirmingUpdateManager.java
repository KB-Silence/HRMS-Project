package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.ConfirmingUpdateService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.ConfirmingUpdateDao;
import kilobyte.hrms.dataAccess.abstracts.EmployeeDao;
import kilobyte.hrms.dataAccess.abstracts.EmployerDao;
import kilobyte.hrms.dataAccess.abstracts.EmployerUpdateDao;
import kilobyte.hrms.entities.concretes.ConfirmingUpdate;
import kilobyte.hrms.entities.concretes.Employer;
import kilobyte.hrms.entities.concretes.EmployerUpdate;

@Service
public class ConfirmingUpdateManager implements ConfirmingUpdateService {

	private EmployeeDao employeeDao;
	private ConfirmingUpdateDao confirmingUpdateDao;
	private EmployerDao employerDao;
	private EmployerUpdateDao employerUpdateDao;

	@Autowired
	public ConfirmingUpdateManager(EmployeeDao employeeDao, ConfirmingUpdateDao confirmingUpdateDao,
			EmployerUpdateDao employerUpdateDao, EmployerDao employerDao) {
		super();
		this.employeeDao = employeeDao;
		this.confirmingUpdateDao = confirmingUpdateDao;
		this.employerUpdateDao = employerUpdateDao;
		this.employerDao = employerDao;
	}

	@Override
	public Result verifyUpdate(int employeeId, int employerId, boolean status) {
		
		EmployerUpdate employerUpdate = this.employerUpdateDao.getByEmployerIdAndApproveStatusFalse(employerId);
		Employer employer = this.employerDao.getOne(employerId);
		ConfirmingUpdate confirmingUpdate = new ConfirmingUpdate();
		
		employerUpdate.setApproveStatus(true);
		employerUpdate.setEmployeeId(employeeId);
		
		this.employerUpdateDao.save(employerUpdate);
		
		employer.setPhoneNumber(employerUpdate.getPhoneNumber());
		employer.setEmail(employerUpdate.getEmail());
		employer.setCompanyName(employerUpdate.getCompanyName());
		employer.setWebSite(employerUpdate.getWebSite());
		employer.setWaitingForUpdate(false);
		
		this.employerDao.save(employer);
		
		confirmingUpdate.setEmployee(this.employeeDao.getOne(employeeId));
		confirmingUpdate.setEmployer(this.employerDao.getOne(employerId));
		confirmingUpdate.setEmployerUpdate(this.employerUpdateDao.getOne(employerUpdate.getUpdateId()));
		confirmingUpdate.setVerifiedStatus(status);
		
		this.confirmingUpdateDao.save(confirmingUpdate);
		
		return new SuccessResult("Güncelleme talebi onaylandı.");
	}

	@Override
	public DataResult<List<ConfirmingUpdate>> getAll() {
		return new SuccessDataResult<List<ConfirmingUpdate>>(this.confirmingUpdateDao.findAll(), "Bütün onaylama işlemleri listelendi.");
	}

	@Override
	public DataResult<List<EmployerUpdate>> getByApproveStatusFalse() {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.getByApproveStatusFalse(), "Onay durumuna göre listelendi.");
	}

	@Override
	public DataResult<List<Employer>> getByWaitingForUpdateTrue() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getByWaitingForUpdateTrue(), "Onay bekleyen iş verenler listelendi.");
	}

}
