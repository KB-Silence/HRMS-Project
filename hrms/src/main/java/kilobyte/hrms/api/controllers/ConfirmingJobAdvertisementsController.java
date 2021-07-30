package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.ConfirmingJobAdvertisementService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;
import kilobyte.hrms.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/confirmingJobAdverts/")
@CrossOrigin
public class ConfirmingJobAdvertisementsController {

	private ConfirmingJobAdvertisementService confirmJobAdvertService;
	
	@Autowired
	public ConfirmingJobAdvertisementsController (ConfirmingJobAdvertisementService confirmJobAdvertService) {
		super();
		this.confirmJobAdvertService = confirmJobAdvertService;
	}
	
	@PostMapping("confirmJobAdverts")
	public ResponseEntity<?> confirmJobAdvertisements(@RequestParam int employeeId, int advertId, boolean status) {
		return ResponseEntityReturn.checkResult(this.confirmJobAdvertService.verify(employeeId, advertId, status));
	}
	
	@GetMapping("getAll")
	public DataResult<List<ConfirmingJobAdvertisement>> getAll() {
		return this.confirmJobAdvertService.getAll();
	}
	
	@GetMapping("getByAdvertIsConfirmed")
	public DataResult<List<JobAdvertisement>> getByAdvertIsConfirmed(@RequestParam boolean status) {
		return this.confirmJobAdvertService.getByAdvertIsConfirmed(status);
	}
	
}
