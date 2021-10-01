package mx.app.petcare.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Entity
public class AdoptionDetails {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@CreatedDate
	private Date adoptedAt;
	
	
	private String status;
	//En proceso
	//Terminado
	
	@ManyToOne
	private Owner owner;
	
	@ManyToOne
	private Owner adopter;
	
	@ManyToOne
	private Pet pet;

}
