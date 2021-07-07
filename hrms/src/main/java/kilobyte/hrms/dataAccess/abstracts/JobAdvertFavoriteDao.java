package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.JobAdvertFavorite;

public interface JobAdvertFavoriteDao extends JpaRepository<JobAdvertFavorite, Integer> {
	List<JobAdvertFavorite> findByUnemployedId(int unemployedId);
	boolean existsByUnemployedIdAndJobAdvertisement_AdvertId(int unemployedId, int advertisementId);

}
