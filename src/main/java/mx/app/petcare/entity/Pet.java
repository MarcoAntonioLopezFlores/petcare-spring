package mx.app.petcare.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Pet {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	private String name;
	private String breed;
	private int age;
	private double growth;
	private String color;
	private String temperament;
	private boolean isForAdopting;
	private boolean isAdopted;
	
	@ManyToOne
	private Owner owner;
	
	
}
