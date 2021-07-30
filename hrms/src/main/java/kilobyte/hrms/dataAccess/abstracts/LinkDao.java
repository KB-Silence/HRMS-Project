package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Link;

public interface LinkDao extends JpaRepository<Link, Integer> {
	
	Link getByUnemployed_UserId(int unemployedId);
}
