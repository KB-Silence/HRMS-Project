package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.ConfirmingJobAdvertisementService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.ConfirmingJobAdvertisement;

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
	
	@PostMapping("add")
	public Result add(@RequestBody ConfirmingJobAdvertisement confirmJobAdvert) {
		return this.confirmJobAdvertService.verify(confirmJobAdvert);
	}
	
	@GetMapping("getAll")
	public DataResult<List<ConfirmingJobAdvertisement>> getAll() {
		return this.confirmJobAdvertService.getAll();
	}
	
}
