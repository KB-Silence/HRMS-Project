package kilobyte.hrms.dataAccess.abstracts;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Unemployed;

public interface UnemployedDao extends JpaRepository<Unemployed, Integer> {
	
	Unemployed getByUserId(int userId);
	Unemployed getByNationalityId(String nationalityId);
	Unemployed getByEmail(String email);
	List<Unemployed> getByMailIsVerifyTrue();
}
