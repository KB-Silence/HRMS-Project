package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.EmploymentTimeService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.dataAccess.abstracts.EmploymentTimeDao;
import kilobyte.hrms.entities.concretes.EmploymentTime;

@Service
public class EmploymentTimeManager implements EmploymentTimeService {

	private EmploymentTimeDao employmentTimeDao;
	
	@Autowired
	public EmploymentTimeManager(EmploymentTimeDao employmentTimeDao) {
		super();
		this.employmentTimeDao = employmentTimeDao;
	}
	
	@Override
	public DataResult<List<EmploymentTime>> getAll() {
		return new SuccessDataResult<List<EmploymentTime>>(this.employmentTimeDao.findAll());
	}

}
