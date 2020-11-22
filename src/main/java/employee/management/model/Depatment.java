package employee.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Depatment {
	
	private long Id;
	private String Dname;
	@Id
	@GeneratedValue
	@Column(name = "EMP_ID")
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

	public Depatment(long id, String dname) {
		super();
		Id = id;
		Dname = dname;
	}

}
