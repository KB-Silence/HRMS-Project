package kilobyte.hrms.core.abstracts;

import kilobyte.hrms.entities.concretes.Unemployed;

public interface MernisService {
	boolean checkIfRealPerson(Unemployed unemployed);
}
