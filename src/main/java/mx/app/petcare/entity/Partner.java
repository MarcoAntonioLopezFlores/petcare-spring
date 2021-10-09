package mx.app.petcare.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Partner {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	private String name;
	private String description;
	@Lob
	private byte[] file;
	private String phone;
	private String type;
	//MORAL
	//FISICA
	private String RFC;
	private boolean status = false;
	
	@OneToOne
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
    private UserAccount user;

}
