package employee.management.model;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDto {

	
	private Address address;
	private Employee employee;
	
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public EmployeeDto(Address address, Employee employee) {
		super();
		this.address = address;
		this.employee = employee;
	}
	public EmployeeDto() {
		// TODO Auto-generated constructor stub
	}
}
