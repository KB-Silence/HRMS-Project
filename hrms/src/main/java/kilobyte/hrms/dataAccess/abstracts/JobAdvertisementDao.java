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

	JobAdvertisement getByAdvertId(int advertId);
	List<JobAdvertisement> getByAdvertStatusTrueOrderByAdvertIdAsc();
	List<JobAdvertisement> getByAdvertStatusTrueAndEmployer_UserId(int employerId);
	List<JobAdvertisement> getByAdvertIsConfirmedFalse();
	List<JobAdvertisement> getByAdvertIsConfirmed(boolean status);
	List<JobAdvertisement> getByAdvertStatusFalseAndEmployer_UserIdOrderByCreatedDateAsc(int employerId);
	List<JobAdvertisement> getByAdvertStatusTrueAndAdvertIsConfirmedTrueAndEmployer_UserIdOrderByCreatedDateAsc(int employerId);
	List<JobAdvertisement> getByAdvertStatusFalseAndAdvertIsConfirmedTrueAndEmployer_UserIdOrderByCreatedDateAsc(int employerId);
	@Query("Select j from kilobyte.hrms.entities.concretes.JobAdvertisement j where ((:#{#filter.cityId}) IS NULL OR j.city.cityId IN (:#{#filter.cityId}))"
	        +" and ((:#{#filter.positionId}) IS NULL OR j.position.positionId IN (:#{#filter.positionId}))"
	        +" and ((:#{#filter.typeId}) IS NULL OR j.employmentType.typeId IN (:#{#filter.typeId}))"
	        +" and ((:#{#filter.timeId}) IS NULL OR j.employmentTime.timeId IN (:#{#filter.timeId}))"
	        +" and j.advertIsConfirmed=true")
	public Page<JobAdvertisement> getByFilter(@Param("filter") JobAdvertFilterDto jobAdvertFilter, Pageable pageable);
}
