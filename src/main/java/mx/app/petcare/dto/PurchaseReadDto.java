package mx.app.petcare.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PurchaseReadDto {

	private long id;
	private String code;
	private Date createdAt;
    private String status;
    private CreditCardReadDto cardCredit;
    private ShoppingCartReadDto shoppingCart;
    
}
