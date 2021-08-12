package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
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
	public ResponseEntity<?> add(@RequestBody JobAdvertisementDto advertisementDto) {
		return ResponseEntityReturn.checkResult(this.advertisementService.add(advertisementDto));
	}

	@PostMapping("getByApprovedAndFilter")
	public Result getByApprovedAndFilter(@RequestBody JobAdvertFilterDto advertFilterDto, @RequestParam int pageNo,
			int pageSize) {
		return advertisementService.getByAdvertIsConfirmedAndPageNumberAndFilter(pageNo, pageSize, advertFilterDto);
	}

	@PutMapping("changeAdvertisementStatus")
	public ResponseEntity<?> changeAdvertisementStatus(@RequestParam int advertId) {
		return ResponseEntityReturn.checkResult(this.advertisementService.changeAdvertisementStatus(advertId));
	}

	@GetMapping("getAll")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.advertisementService.getAll();
	}

	@GetMapping("getByAdvertStatusTrue")
	public DataResult<List<JobAdvertisement>> getByAdvertStatusTrue() {
		return this.advertisementService.getByAdvertStatusTrue();
	}

	@GetMapping("getByAdvertStatusFalseAndEmployerIdSorted")
	public ResponseEntity<?> getByAdvertStatusFalseAndEmployerIdSorted(@RequestParam int employerId) {
		return ResponseEntityReturn
				.checkResult(this.advertisementService.getByAdvertStatusAndEmployerIdSorted(employerId));
	}

	@GetMapping("getByAdvertStatusTrueAndEmployerId")
	public DataResult<List<JobAdvertisement>> getByAdvertStatusTrueAndEmployerId(@RequestParam int employerId) {
		return this.advertisementService.getByAdvertStatusTrueAndEmployerId(employerId);
	}

	@GetMapping("getByAdvertIsConfirmed")
	public DataResult<List<JobAdvertisement>> getByAdvertIsConfirmed(@RequestParam boolean status) {
		return this.advertisementService.getByAdvertIsConfirmed(status);
	}

	@GetMapping("getByAdvertId")
	public DataResult<JobAdvertisement> getByAdvertId(@RequestParam int advertId) {
		return this.advertisementService.getByAdvertId(advertId);
	}

	@GetMapping("getByAdvertStatusAndAdvertIsConfirmedAndEmployerId")
	public DataResult<List<JobAdvertisement>> getByAdvertStatusAndAdvertIsConfirmedAndEmployerId(
			@RequestParam int employerId) {
		return this.advertisementService.getByAdvertStatusAndAdvertIsConfirmedAndEmployerId(employerId);
	}

	@GetMapping("getByAdvertStatusFalseAndAdvertIsConfirmedTrueAndEmployerId")
	public ResponseEntity<?> getByAdvertStatusFalseAndAdvertIsConfirmedTrueAndEmployerId(@RequestParam int employerId) {
		return ResponseEntityReturn.checkResult(
				this.advertisementService.getByAdvertStatusFalseAndAdvertIsConfirmedTrueAndEmployerId(employerId));
	}
}
