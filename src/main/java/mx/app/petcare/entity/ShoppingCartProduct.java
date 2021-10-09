package mx.app.petcare.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class ShoppingCartProduct {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private ShoppingCart shoppingCart;
	
	private int quantity;

    private double subtotal;

   
}
