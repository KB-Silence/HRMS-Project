package kilobyte.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kilobyte.hrms.entities.concretes.Education;

public interface EducationDao extends JpaRepository<Education, Integer>{
	
	Education getBySchoolNameAndDepartmentAndUnemployed_UserId(String schoolName, String departmen, int userId);
	List<Education> getByUnemployed_UserIdOrderByGraduatedDateDesc(int unemployedId);
}
