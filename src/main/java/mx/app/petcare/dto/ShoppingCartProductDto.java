package mx.app.petcare.dto;

import lombok.Data;

@Data
public class ShoppingCartProductDto {

	private long id;	
	private ProductDto product;
	private ShoppingCartDto shoppingCart;	
	private int quantity;
    private double subtotal; 
}
