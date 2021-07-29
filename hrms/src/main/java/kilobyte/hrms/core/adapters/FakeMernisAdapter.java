package kilobyte.hrms.core.adapters;

import org.springframework.stereotype.Service;

import kilobyte.hrms.core.abstracts.MernisService;
import kilobyte.hrms.entities.dtos.UnemployedRegisterDto;

@Service
public class FakeMernisAdapter implements MernisService{

	@Override
	public boolean checkIfRealPerson(UnemployedRegisterDto unemployedDto) {
		return true;
	}

}
