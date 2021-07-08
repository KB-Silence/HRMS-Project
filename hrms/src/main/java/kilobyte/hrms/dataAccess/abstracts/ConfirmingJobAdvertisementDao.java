package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;

public interface ConfirmingJobAdvertisementDao extends JpaRepository<ConfirmingJobAdvertisement, Integer>{
	
}
