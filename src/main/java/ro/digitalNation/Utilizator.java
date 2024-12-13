package ro.digitalNation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Utilizator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String Utilizatorname;
    private String email;
    
	public Utilizator(Long id, String Utilizatorname, String email) {
		super();
		this.id = id;
		this.Utilizatorname = Utilizatorname;
		this.email = email;
	}
	
	public Utilizator(String Utilizatorname, String email) {
		super();
		this.Utilizatorname = Utilizatorname;
		this.email = email;
	}
	public Utilizator() {}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUtilizatorname() {
		return Utilizatorname;
	}
	public void setUtilizatorname(String Utilizatorname) {
		this.Utilizatorname = Utilizatorname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
