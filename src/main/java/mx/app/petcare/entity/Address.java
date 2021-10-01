package mx.app.petcare.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Address {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	private String state;
	private String town;
	private String ville;
	private String street;
}
