package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.EmployeeService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.concretes.Employee;
import kilobyte.hrms.entities.dtos.EmployeeUpdateDto;

@RestController
@RequestMapping("/api/employees/")
@CrossOrigin
public class EmployeesController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeesController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping("addEmployee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		return ResponseEntityReturn.checkResult(this.employeeService.addEmployee(employee));
		
	}
	
	@PutMapping("updateEmployee")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeUpdateDto employeeDto) {
		return ResponseEntityReturn.checkResult(this.employeeService.updateEmployee(employeeDto));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.employeeService.getAll());
	}

}
