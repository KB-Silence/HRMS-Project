package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.JobExperienceService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.dtos.JobExperienceDto;

@RestController
@RequestMapping("/api/jobexperiences/")
@CrossOrigin
public class JobExperiencesController {

	private JobExperienceService jobExperienceService;

	@Autowired
	public JobExperiencesController(JobExperienceService jobExperienceService) {
		super();
		this.jobExperienceService = jobExperienceService;
	}

	@PostMapping("addJobExperience")
	public ResponseEntity<?> addJobExperience(@RequestBody JobExperienceDto jobExperienceDto) {
		return ResponseEntityReturn.checkResult(this.jobExperienceService.addJobExperience(jobExperienceDto));
	}

	@PutMapping("updateJobExperience")
	public ResponseEntity<?> updateJobExperience(@RequestBody JobExperienceDto jobExperienceDto, int experienceId) {
		return ResponseEntityReturn
				.checkResult(this.jobExperienceService.updateJobExperience(jobExperienceDto, experienceId));
	}

	@DeleteMapping("deleteJobExperience")
	public ResponseEntity<?> deleteJobExperience(@RequestParam int experienceId) {
		return ResponseEntityReturn.checkResult(this.jobExperienceService.deleteJobExperience(experienceId));
	}

	@GetMapping("getAllJobExperiences")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.jobExperienceService.getAll());
	}

	@GetMapping("getByUnemployedIdOrderByLeaveDate")
	public ResponseEntity<?> getByUnemployedIdOrderByLeaveDate(@RequestParam int unemployedId) {
		return ResponseEntity.ok(this.jobExperienceService.getByUnemployedIdOrderByLeaveDateDesc(unemployedId));
	}
}
