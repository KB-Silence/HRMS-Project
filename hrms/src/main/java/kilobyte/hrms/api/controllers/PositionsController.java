package kilobyte.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.PositionService;
import kilobyte.hrms.core.utilities.results.DataResult;
import kilobyte.hrms.core.utilities.results.Result;
import kilobyte.hrms.entities.concretes.Position;

@RestController
@RequestMapping("/api/positions")
@CrossOrigin
public class PositionsController {
	
	private PositionService positionService;

	@Autowired
	public PositionsController(PositionService positionService) {
		super();
		this.positionService = positionService;
	}
	
	@PostMapping("/addPosition")
	public Result addPosition(@RequestBody Position position) {
		return this.positionService.addPosition(position);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Position>> getAll() {
		return this.positionService.getAll();
	}
}
