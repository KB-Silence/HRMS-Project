package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.JobAdvertisementService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobAdvertisements/")
@CrossOrigin
public class JobAdvertisementsController {
	
	private JobAdvertisementService advertisementService;
	
	@Autowired
	public JobAdvertisementsController(JobAdvertisementService advertisementService) {
		super();
		this.advertisementService = advertisementService;
	}
	
	@PostMapping("addAdvertisement")
	public Result add(@RequestBody JobAdvertisement advertisement) {
		return this.advertisementService.add(advertisement);
	}
	
	@PutMapping("changeAdvertisementStatus")
	public Result changeAdvertisementStatus(@RequestParam int advertId, int employerId, boolean status) {
		return this.advertisementService.changeAdvertisementStatus(advertId, employerId, status);
	}
	
	@GetMapping("getAll")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.advertisementService.getAll();
	}
	
	@GetMapping("getAllByActive")
	public DataResult<List<JobAdvertisement>> getAllByActive() {
		return this.advertisementService.getAllByActive();
	}
	
	@GetMapping("getAllByActiveSorted")
	public DataResult<List<JobAdvertisement>> getAllByActiveSorted() {
		return this.advertisementService.getAllByActiveSorted();
	}
	
	@GetMapping("getAllActiveByEmployerId")
	public DataResult<List<JobAdvertisement>> getAllActiveByEmployerId(@RequestParam int employerId) {
		return this.advertisementService.getAllActiveByEmployerId(employerId);
	}
}
