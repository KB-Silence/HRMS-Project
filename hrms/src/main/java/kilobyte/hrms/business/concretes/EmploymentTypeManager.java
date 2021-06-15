package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.EmploymentTypeService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.dataAccess.abstracts.EmploymentTypeDao;
import kilobyte.hrms.entities.concretes.EmploymentType;

@Service
public class EmploymentTypeManager implements EmploymentTypeService{

	private EmploymentTypeDao employmentTypeDao;
	
	@Autowired
	public EmploymentTypeManager(EmploymentTypeDao employmentTypeDao) {
		super();
		this.employmentTypeDao = employmentTypeDao;
	}
	
	@Override
	public DataResult<List<EmploymentType>> getAll() {
		return new SuccessDataResult<List<EmploymentType>>(this.employmentTypeDao.findAll());
	}

}
