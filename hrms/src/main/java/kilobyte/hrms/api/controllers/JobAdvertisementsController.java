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
import kilobyte.hrms.entities.dtos.JobAdvertFilterDto;
import kilobyte.hrms.entities.dtos.JobAdvertisementDto;

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
	public Result add(@RequestBody JobAdvertisementDto advertisementDto) {
		return this.advertisementService.add(advertisementDto);
	}
	
	@PostMapping("getByApprovedAndFilter")
	public Result getByApprovedAndFilter(@RequestParam int pageNo, int pageSize, @RequestBody JobAdvertFilterDto advertFilterDto) {
		return advertisementService.getByAdvertIsConfirmedAndPageNumberAndFilter(pageNo, pageSize, advertFilterDto);
	}
	
	@PutMapping("changeAdvertisementStatus")
	public Result changeAdvertisementStatus(@RequestParam int advertId, boolean status) {
		return this.advertisementService.changeAdvertisementStatus(advertId, status);
	}
	
	@GetMapping("getAll")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.advertisementService.getAll();
	}
	
	@GetMapping("getByAdvertStatus")
	public DataResult<List<JobAdvertisement>> getByAdvertStatus() {
		return this.advertisementService.getByAdvertStatus();
	}
	
	@GetMapping("getByAdvertStatusAndSorted")
	public DataResult<List<JobAdvertisement>> getByAdvertStatusAndSorted() {
		return this.advertisementService.getByAdvertStatusAndSorted();
	}
	
	@GetMapping("getByAdvertStatusTrueAndEmployerId")
	public DataResult<List<JobAdvertisement>> getByAdvertStatusTrueAndEmployerId(@RequestParam int employerId) {
		return this.advertisementService.getByAdvertStatusTrueAndEmployerId(employerId);
	}
	
	@GetMapping("getAllApproved")
	public DataResult<List<JobAdvertisement>> getAllApproved(@RequestParam boolean status) {
		return this.advertisementService.getAllApproved(status);
	}
}
