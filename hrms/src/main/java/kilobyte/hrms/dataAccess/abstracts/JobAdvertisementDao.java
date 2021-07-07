package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kilobyte.hrms.entities.concretes.JobAdvertisement;
import kilobyte.hrms.entities.dtos.JobAdvertFilterDto;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {

	@Query("From JobAdvertisement where advertStatus=true")
	List<JobAdvertisement> getAllByActive();

	@Query("From JobAdvertisement where advertStatus=true Order By createdDate ASC")
	List<JobAdvertisement> getAllByActiveSorted();

	@Query("From JobAdvertisement where advertStatus=true and employer_id=:employerId")
	List<JobAdvertisement> getAllActiveByEmployerId(int employerId);

	List<JobAdvertisement> getAllByConfirmingJobAdvertisement_VerifiedStatus(boolean verified);

	JobAdvertisement getByAdvertIdAndEmployerId(int advertId, int employerId);

	@Query("Select j from kilobyte.hrms.entities.concretes.JobAdvertisement j where ((:#{#filter.cityId}) IS NULL OR j.city.cityId IN (:#{#filter.cityId}))"
			+" and ((:#{#filter.positionId}) IS NULL OR j.position.positionId IN (:#{#filter.positionId}))"
			+" and ((:#{#filter.typeId}) IS NULL OR j.employmentType.typeId IN (:#{#filter.typeId}))"
			+" and ((:#{#filter.timeId}) IS NULL OR j.employmentTime.timeId IN (:#{#filter.timeId}))"
			+" and j.advertStatus=true")
	public Page<JobAdvertisement> getByFilter(@Param("filter") JobAdvertFilterDto jobAdvertFilter, Pageable pageable);
}
