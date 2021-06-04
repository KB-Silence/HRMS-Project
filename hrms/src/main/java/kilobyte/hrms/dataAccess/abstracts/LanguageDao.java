package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Language;

public interface LanguageDao extends JpaRepository<Language, Integer>{

	List<Language> getByUnemployedId(int unemployedId);
}