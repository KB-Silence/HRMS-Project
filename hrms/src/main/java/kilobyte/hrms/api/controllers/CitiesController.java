package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.CityService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.entities.concretes.City;

@RestController
@RequestMapping("/api/cities/")
@CrossOrigin
public class CitiesController {

	private CityService cityService;
	
	@Autowired
	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("getAllCities")
	public DataResult<List<City>> getAllCities() {
		return this.cityService.getAll();
	}
	
}
