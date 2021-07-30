package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Position;

public interface PositionDao extends JpaRepository<Position, Integer>{
	
	Position findByPositionName(String name);
}
