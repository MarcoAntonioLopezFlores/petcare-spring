package mx.app.petcare.dto;

import lombok.Data;

@Data
public class PurchaseDto {

	private long id;
	private String code;    
    private String status;
    private CreditCardDto cardCredit;
    private ShoppingCartDto shoppingCart;
    private PersonDto person;
}
