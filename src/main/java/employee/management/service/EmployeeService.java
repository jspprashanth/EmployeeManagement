package employee.management.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import employee.management.model.Employee;
import employee.management.repository.EmployeeRepository;

@Service
@Component
@Profile("prod") // Means this bean will be consider only for prod profile
@Transactional(value = TxType.REQUIRES_NEW)
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployeeById(long id) {
		return employeeRepository.findById(id);
	}

	public Optional<Employee> deleteEmployee(long id) {
		return employeeRepository.findById(id);
	}

	public Employee addEmployee(Employee employee) {		

		return employeeRepository.saveAndFlush(employee);

	}

}