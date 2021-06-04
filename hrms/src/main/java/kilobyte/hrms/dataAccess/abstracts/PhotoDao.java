package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Photo;

public interface PhotoDao extends JpaRepository<Photo, Integer>{
	Photo getByUnemployedId(int unemployedId);
}
