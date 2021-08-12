package kilobyte.hrms.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kilobyte.hrms.entities.concretes.CoverLetter;
import kilobyte.hrms.entities.concretes.Education;
import kilobyte.hrms.entities.concretes.JobExperience;
import kilobyte.hrms.entities.concretes.Language;
import kilobyte.hrms.entities.concretes.Link;
import kilobyte.hrms.entities.concretes.Photo;
import kilobyte.hrms.entities.concretes.Technology;
import kilobyte.hrms.entities.concretes.Unemployed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvDto {
	
	@JsonIgnoreProperties({"id","password"})
	private Unemployed unemployed;
	
	@JsonIgnoreProperties({"unemployed"})
	private List<Education> educations;
	
	@JsonIgnoreProperties({"unemployed"})
	private List<JobExperience> jobExperiences;
	
	@JsonIgnoreProperties({"unemployed"})
	private List<Technology> technologies;
	
	@JsonIgnoreProperties({"unemployed"})
	private List<Language> languages;
	
	@JsonIgnoreProperties({"unemployed"})
	private Link link;
	
	@JsonIgnoreProperties({"unemployed"})
	private CoverLetter coverLetter;
	
	@JsonIgnoreProperties({"unemployed"})
	private Photo photo;
}
