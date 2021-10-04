package mx.app.petcare.dto;

import lombok.Data;
@Data
public class AdoptionDetailsDto {

	private long id;	
	private String status;
	//Solicitada
	//En proceso
	//Terminada
	
	private PersonDto owner;
	private PersonDto adopter;
	private PetDto pet;
}
