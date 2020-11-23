package employee.management.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	private long Id;
	private String Name;
	private Address address;

	private Depatment department;
	private List<EmployeeQualification> employeeQualification;

	public Employee() {
	}

	public Employee(String name, Address address) {
		super();
		Name = name;
		this.address = address;
	}

	@Id
	@GeneratedValue
	@Column(name = "EMP_ID",nullable = true)
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	@Column(name = "NAME",  length = 100)
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Address getAddress() {
		return address;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Depatment getDepartment() {
		return department;
	}

	public void setDepartment(Depatment department) {
		this.department = department;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<EmployeeQualification> getEmployeeQualification() {
		return employeeQualification;
	}

	public void setEmployeeQualification(List<EmployeeQualification> employeeQualification) {
		this.employeeQualification = employeeQualification;
	}

	public Employee(long id, String name, Address address, Depatment department,
			List<EmployeeQualification> employeeQualifications) {
		Id = id;
		Name = name;
		this.address = address;
		this.department = department;
		this.employeeQualification = employeeQualifications;
	}

}
