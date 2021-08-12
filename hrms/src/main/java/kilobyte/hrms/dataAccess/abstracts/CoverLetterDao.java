package kilobyte.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.CoverLetter;

public interface CoverLetterDao extends JpaRepository<CoverLetter, Integer>{
	
	CoverLetter getByUnemployed_UserId(int unemployedId);
	CoverLetter getByLetterContent(String letterContent);
	
}
