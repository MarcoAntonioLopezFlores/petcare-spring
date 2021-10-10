package mx.app.petcare.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ShoppingCartReadDto {

	private long id;	
	private double total;
	private Date createdAt;   
}
