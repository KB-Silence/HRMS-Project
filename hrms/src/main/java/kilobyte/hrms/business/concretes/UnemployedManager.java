package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.UnemployedService;
import kilobyte.hrms.core.abstracts.MernisService;
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
	private MernisService mernisService;

	@Autowired
	public UnemployedManager(UnemployedDao unemployedDao, MernisService mernisService) {
		super();
		this.unemployedDao = unemployedDao;
		this.mernisService = mernisService;
	}

	@Override
	public Result addUnemployed(Unemployed unemployed) {

		if (this.unemployedDao.findByNationalityId(unemployed.getNationalityId()) == null) {
			if (this.mernisService.checkIfRealPerson(unemployed)) {
				this.unemployedDao.save(unemployed);
				return new SuccessResult("Yeni iş arayan eklendi.");
			}
		}
		return new ErrorResult("Kayıt olma başarısız. Bilgileri kontrol edin ve tekrar deneyin.");
	}

	@Override
	public DataResult<List<Unemployed>> getAll() {
		return new SuccessDataResult<List<Unemployed>>(this.unemployedDao.findAll(), "İş arayanlar listelendi.");
	}

}
