package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.ConfirmingUpdate;

public interface ConfirmingUpdateDao extends JpaRepository<ConfirmingUpdate, Integer>{

}
