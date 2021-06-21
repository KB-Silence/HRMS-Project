package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kilobyte.hrms.entities.concretes.ConfirmingEmployer;
import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;

public interface ConfirmingEmployerDao extends JpaRepository<ConfirmingEmployer, Integer>{

	@Query("From ConfirmingEmployer where verifiedStatus=true")
	List<ConfirmingJobAdvertisement> getAllEmployerByApproved();
	
}
