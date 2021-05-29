package kilobyte.hrms.core.adapters;

import org.springframework.stereotype.Service;

import kilobyte.hrms.core.abstracts.MernisService;
import kilobyte.hrms.entities.concretes.Unemployed;

@Service
public class FakeMernisAdapter implements MernisService{

	@Override
	public boolean checkIfRealPerson(Unemployed unemployed) {
		return true;
	}

}
