package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Technology;

public interface TechnologyDao extends JpaRepository<Technology, Integer> {

	Technology getByTechnologyNameAndUnemployed_UserId(String technologyName, int unemployedId);
	Technology getByTechnologyName(String technologyName);
	List<Technology> getByUnemployed_UserId(int unemployedId);
}
