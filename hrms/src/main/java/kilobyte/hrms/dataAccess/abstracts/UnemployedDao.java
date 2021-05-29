package kilobyte.hrms.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Unemployed;

public interface UnemployedDao extends JpaRepository<Unemployed, Integer> {
	Unemployed findByNationalityId(String nationalityId);
}
