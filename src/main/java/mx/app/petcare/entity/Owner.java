package mx.app.petcare.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Owner {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@OneToOne
    private UserAccount userAccount;
	
	@ManyToOne
	private Address address;
	
}
