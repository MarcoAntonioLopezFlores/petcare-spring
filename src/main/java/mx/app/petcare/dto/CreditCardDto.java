package mx.app.petcare.dto;

import lombok.Data;

@Data
public class CreditCardDto {

	private long id;
	private String number;
	private PersonDto person;
}
