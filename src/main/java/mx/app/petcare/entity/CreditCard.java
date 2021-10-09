package mx.app.petcare.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class CreditCard {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;

	private String number;
	
	@ManyToOne
	private Person person;
}
