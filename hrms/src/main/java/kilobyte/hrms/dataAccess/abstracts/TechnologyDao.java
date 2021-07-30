package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Technology;

public interface TechnologyDao extends JpaRepository<Technology, Integer>{

	List<Technology> getByUnemployed_UserId(int unemployedId);
}
