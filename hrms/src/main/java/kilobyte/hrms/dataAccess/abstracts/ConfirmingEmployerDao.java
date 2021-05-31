package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.ConfirmingEmployer;

public interface ConfirmingEmployerDao extends JpaRepository<ConfirmingEmployer, Integer>{

}
