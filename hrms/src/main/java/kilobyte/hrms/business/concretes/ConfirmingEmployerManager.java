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
import kilobyte.hrms.entities.concretes.ConfirmingEmployer;

@Service
public class ConfirmingEmployerManager implements ConfirmingEmployerService {

	private ConfirmingEmployerDao confirmingEmployerDao;
	
	@Autowired
	public ConfirmingEmployerManager(ConfirmingEmployerDao confirmingEmployerDao) {
		super();
		this.confirmingEmployerDao = confirmingEmployerDao;
	}
	
	@Override
	public Result verify(ConfirmingEmployer confirmEmployer) {
		this.confirmingEmployerDao.save(confirmEmployer);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<ConfirmingEmployer>> getAll() {
		return new SuccessDataResult<List<ConfirmingEmployer>>(this.confirmingEmployerDao.findAll());
	}

}
