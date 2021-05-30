package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.City;

public interface CityDao extends JpaRepository<City, Integer>{

}
