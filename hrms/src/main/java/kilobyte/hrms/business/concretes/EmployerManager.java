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
import kilobyte.hrms.dataAccess.abstracts.EmployerUpdateDao;
import kilobyte.hrms.entities.concretes.Employer;
import kilobyte.hrms.entities.concretes.EmployerUpdate;
import kilobyte.hrms.entities.dtos.EmployerRegisterDto;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private EmployerUpdateDao updateDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao, EmployerUpdateDao updateDao) {
		super();
		this.employerDao = employerDao;
		this.updateDao = updateDao;
	}

	@Override
	public Result addEmployer(EmployerRegisterDto employerDto) {
		if (this.checkEmailDomain(employerDto.getEmail(), employerDto.getWebSite()).isSuccess()) {
			Employer employer = new Employer();
			employer.setCompanyName(employerDto.getCompanyName());
			employer.setWebSite(employerDto.getWebSite());
			employer.setEmail(employerDto.getEmail());
			employer.setPassword(employerDto.getPassword());
			employer.setPhoneNumber(employerDto.getPhoneNumber());
			this.employerDao.save(employer);
			return new SuccessResult("Kayıt işlemi başarılı.");
		}
		return new ErrorResult("Domain doğrulama başarısız lütfen tekrar deneyin.");
	}

	@Override
	public Result updateEmployer(EmployerUpdate employerUpdate) {
		employerUpdate.setEmployeeId(null);
		if (!this.employerDao.existsById(employerUpdate.getEmployerId())) {
			return new ErrorResult("İşveren bulunamadı.");
		}
		Employer employer = this.employerDao.getOne(employerUpdate.getEmployerId());
		this.updateDao.save(employerUpdate);
		employer.setWaitingForUpdate(true);
		this.employerDao.save(employer);
		return new SuccessResult(
				"Güncelleme talebiniz alındı. İlgili personel tarafından kontrol edildikten sonra onaylanacaktır.");
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

	@Override
	public DataResult<List<Employer>> getByMailIsVerifyTrue() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getByMailIsVerifyTrue());
	}

	@Override
	public DataResult<Employer> getByUserId(int userId) {
		return new SuccessDataResult<Employer>(this.employerDao.getByUserId(userId));
	}
}
