package mx.app.petcare.dto;

import lombok.Data;

@Data
public class ShoppingCartProductReadDto {

	private long id;	
	private ProductReadDto product;
	private int quantity;
    private double subtotal; 
}
