package employee.management.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee.management.exception.ResourceNotFoundException;
import employee.management.model.Employee;
import employee.management.model.EmployeeDto;
import employee.management.service.EmployeeService;
import employee.management.utill.JsonNullableUtils;

@RestController
@RequestMapping("/api/v1")

public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		logger.info("{}", messageSource.getMessage("l1", null, Locale.GERMAN));

		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setAddress(employeeDetails.getAddress());
		employee.setDepartment(employeeDetails.getDepartment());
		employee.setEmployeeQualification(employeeDetails.getEmployeeQualification());
		final Employee updatedEmployee = employeeService.addEmployee(employee);

		logger.info("{}", messageSource.getMessage("l1", null, Locale.ENGLISH));
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		employeeService.deleteEmployee(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PatchMapping("/employees/{id}")
	public ResponseEntity<Employee> partialUpdateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		if (employeeDetails.getDepartment() != null) {
			employee.getDepartment().setDname(employeeDetails.getDepartment().getDname());
		}
		if (employeeDetails.getAddress() != null) {
			employeeDetails.getAddress().setCity(employeeDetails.getAddress().getCity());
		}

		if (employeeDetails.getEmployeeQualification() != null) {
			employeeDetails.setEmployeeQualification(employee.getEmployeeQualification());
		}

		final Employee updatedEmployee = employeeService.addEmployee(employee);
		return ResponseEntity.ok(updatedEmployee);

	}

}