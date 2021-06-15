package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;

public interface ConfirmingJobAdvertisementDao extends JpaRepository<ConfirmingJobAdvertisement, Integer>{
	
	@Query("From ConfirmingJobAdvertisement where verifiedStatus=true")
	List<ConfirmingJobAdvertisement> getAllByApproved();
}
