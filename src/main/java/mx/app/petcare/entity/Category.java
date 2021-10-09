package mx.app.petcare.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Category {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;

	private String name;
	private String description;
	private boolean status = true;
}
