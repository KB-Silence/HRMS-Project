package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.UnemployedService;
import kilobyte.hrms.business.abstracts.UserService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.UnemployedDao;
import kilobyte.hrms.entities.concretes.Unemployed;

@Service
public class UnemployedManager implements UnemployedService {
	
	private UnemployedDao unemployedDao;
	private UserService userService;
	
	@Autowired
	public UnemployedManager(UnemployedDao unemployedDao, UserService userService) {
		super();
		this.unemployedDao = unemployedDao;
		this.userService = userService;
	}
	
	private DataResult<Unemployed> nationalityIdIsExist(String nationalityId) {
		return new SuccessDataResult<Unemployed>(this.unemployedDao.findByNationalityId(nationalityId));
	}
	
	@Override
	public Result add(Unemployed unemployed) {
		if((this.userService.checkEmail(unemployed.getEmail()).getData() != null) && (this.nationalityIdIsExist(unemployed.getNationalityId()).getData() != null )) {
			return new ErrorResult("E-Posta adresi ya da TC No zaten mevcut.");
		}
		
		this.unemployedDao.save(unemployed);
		return new SuccessResult("Yeni iş arayan eklendi.");
	}

	@Override
	public DataResult<List<Unemployed>> getAll() {
		return new SuccessDataResult<List<Unemployed>>(this.unemployedDao.findAll(), "İş arayanlar listelendi.");
	}

}
