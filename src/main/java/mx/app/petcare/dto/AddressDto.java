package mx.app.petcare.dto;

import lombok.Data;

@Data
public class AddressDto {
	private long id;	
	private String state;
	private String town;
	private String ville;
	private String street;
	private PersonDto person;
}
