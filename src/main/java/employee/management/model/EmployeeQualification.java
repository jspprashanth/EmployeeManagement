package employee.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeQualification {
	private long Id;
	private String Dname;
	private Employee employee;

	@Id
	@GeneratedValue
	@Column(name = "Q_ID")
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	public EmployeeQualification(long id, String dname) {
		Id = id;
		Dname = dname;
	}

	public EmployeeQualification() {

	}

	public EmployeeQualification(long id, String dname, Employee employee) {
		Id = id;
		Dname = dname;
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable = true)
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
