package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.JobExperienceService;
import kilobyte.hrms.entities.concretes.JobExperience;

@RestController
@RequestMapping("/api/jobexperiences/")
public class JobExperiencesController {

	private JobExperienceService jobExperienceService;

	@Autowired
	public JobExperiencesController(JobExperienceService jobExperienceService) {
		super();
		this.jobExperienceService = jobExperienceService;
	}

	@PostMapping("addJobExperience")
	public ResponseEntity<?> addJobExperience(@RequestBody JobExperience jobExperience) {
		return ResponseEntity.ok(this.jobExperienceService.addJobExperience(jobExperience));
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
