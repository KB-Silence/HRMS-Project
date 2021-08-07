package kilobyte.hrms.business.abstracts;

import java.util.List;

import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Unemployed;
import kilobyte.hrms.entities.dtos.UnemployedRegisterDto;

public interface UnemployedService {
	
	Result addUnemployed(UnemployedRegisterDto unemployedDto);
	DataResult<Unemployed> getByUserId(int userId);
	DataResult<List<Unemployed>> getAll();
	DataResult<Unemployed> getByNationalityId(String nationalityId);
	DataResult<Unemployed> getByEmail(String email);
	DataResult<List<Unemployed>> getByMailIsVerifyTrue();
}
