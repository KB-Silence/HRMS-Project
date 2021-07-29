package kilobyte.hrms.core.abstracts;

import kilobyte.hrms.entities.dtos.UnemployedRegisterDto;

public interface MernisService {
	boolean checkIfRealPerson(UnemployedRegisterDto unemployedDto);
}
