package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.EmploymentTypeService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.EmploymentType;

@RestController
@RequestMapping("/api/employmentTypes/")
@CrossOrigin
public class EmploymentTypesController {
	
	private EmploymentTypeService employmentTypeService;
	
	@Autowired
	public EmploymentTypesController(EmploymentTypeService employmentTypeService) {
		super();
		this.employmentTypeService = employmentTypeService;
	}
	
	@GetMapping("getAll")
	public DataResult<List<EmploymentType>> getAll() {
		return this.employmentTypeService.getAll();
	}
}
