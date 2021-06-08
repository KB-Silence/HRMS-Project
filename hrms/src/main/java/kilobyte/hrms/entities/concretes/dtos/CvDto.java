package kilobyte.hrms.entities.concretes.dtos;

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
	
	@JsonIgnoreProperties({"unemployed","educationId"})
	private List<Education> educations;
	
	@JsonIgnoreProperties({"unemployed","experienceId"})
	private List<JobExperience> jobExperiences;
	
	@JsonIgnoreProperties({"unemployed","technologyId"})
	private List<Technology> technologies;
	
	@JsonIgnoreProperties({"unemployed","languageId"})
	private List<Language> languages;
	
	@JsonIgnoreProperties({"unemployed","linkId"})
	private Link link;
	
	@JsonIgnoreProperties({"unemployed","letterId"})
	private List<CoverLetter> coverLetters;
	
	@JsonIgnoreProperties({"unemployed","photoId"})
	private Photo photo;
}
