package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.JobExperience;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer>{

	JobExperience getByWorkplaceNameAndPositionNameAndUnemployed_UserId(String workplaceName, String positionName, int unemployedId);
	List<JobExperience> getByUnemployed_UserIdOrderByLeaveDateDesc(int unemployedId);
}
