package mx.app.petcare.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ShoppingCart {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private boolean status=true;
	private double total;
	
	private Date createdAt = new Date();
    @ManyToOne
    private Person person;
}
