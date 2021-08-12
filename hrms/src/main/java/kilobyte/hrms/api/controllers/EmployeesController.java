package kilobyte.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kilobyte.hrms.business.abstracts.EmployeeService;
import kilobyte.hrms.core.utilities.utils.ResponseEntityReturn;
import kilobyte.hrms.entities.dtos.EmployeeDto;

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
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
		return ResponseEntityReturn.checkResult(this.employeeService.addEmployee(employeeDto));
		
	}
	
	@PutMapping("updateEmployee")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto, @RequestParam int employeeId) {
		return ResponseEntityReturn.checkResult(this.employeeService.updateEmployee(employeeDto, employeeId));
	}
	
	@DeleteMapping("deleteEmployee")
	public ResponseEntity<?> deleteEmployee(@RequestParam int employeeId) {
		return ResponseEntityReturn.checkResult(this.employeeService.deleteEmployee(employeeId));
	}
	
	@GetMapping("getByUserId")
	public ResponseEntity<?> getByUserId(@RequestParam int userId) {
		return ResponseEntityReturn.checkResult(this.employeeService.getByUserId(userId));
	}
	
	@GetMapping("getAll")
	public ResponseEntity<?> getAll() {
		return ResponseEntityReturn.checkResult(this.employeeService.getAll());
	}

}
