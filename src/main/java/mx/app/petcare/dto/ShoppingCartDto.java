package mx.app.petcare.dto;

import lombok.Data;

@Data
public class ShoppingCartDto {

	private long id;	
	private double total;
    private PersonDto person;
}
