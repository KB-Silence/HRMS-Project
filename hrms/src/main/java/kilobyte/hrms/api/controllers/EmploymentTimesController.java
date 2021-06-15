package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.EmploymentTimeService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.EmploymentTime;

@RestController
@RequestMapping("/api/employmentTimes/")
public class EmploymentTimesController {
	
	private EmploymentTimeService employmentTimeService;
	
	@Autowired
	public EmploymentTimesController(EmploymentTimeService employmentTimeService) {
		super();
		this.employmentTimeService = employmentTimeService;
	}

	@GetMapping("getAll")
	public DataResult<List<EmploymentTime>> getAll() {
		return this.employmentTimeService.getAll();
	}
}
