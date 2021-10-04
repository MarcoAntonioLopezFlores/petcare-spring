package mx.app.petcare.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AdoptionDetailsReadDto {

	private long id;	
	private String status;
	//Solicitada
	//En proceso
	//Terminada
	private Date startedAt;
	private PersonReadDto owner;
	private PersonReadDto adopter;
	private PetReadDto pet;
}
