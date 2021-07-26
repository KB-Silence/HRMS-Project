package kilobyte.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kilobyte.hrms.business.abstracts.EmployerService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.ErrorResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.core.utilities.results.SuccessDataResult;
import kilobyte.hrms.core.utilities.results.SuccessResult;
import kilobyte.hrms.dataAccess.abstracts.EmployerDao;
import kilobyte.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public Result addEmployer(Employer employer) {
		if (this.checkEmailDomain(employer.getEmail(), employer.getWebSite()).isSuccess()) {
			this.employerDao.save(employer);
			return new SuccessResult("Yeni işveren eklendi.");
		} else {
			return new ErrorResult("Domain Kontrolü Başarısız.");
		}
	}

	@Override
	public Result checkEmailDomain(String email, String domain) {
		String[] mails = email.split("@", 2);
		String web = domain.substring(4);
		if (mails[1].equals(web)) {
			return new SuccessResult("Domain kontrolü başarılı.");
		}
		return new ErrorResult("Domain kontrolü başarısız.");
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "İş verenler listelendi.");
	}
}
