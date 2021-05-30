package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kilobyte.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
	
	@Query("From JobAdvertisement where advertStatus=true")
	List<JobAdvertisement> getAllByActive();
	
	@Query("From JobAdvertisement where advertStatus=true Order By createdDate ASC")
	List<JobAdvertisement> getAllByActiveSorted();
	
	@Query("From JobAdvertisement where advertStatus=true and employer_id=:employerId")
	List<JobAdvertisement> getAllActiveByEmployerId(int employerId);
	
	JobAdvertisement getByAdvertIdAndEmployerId(int advertId, int employerId);
}
