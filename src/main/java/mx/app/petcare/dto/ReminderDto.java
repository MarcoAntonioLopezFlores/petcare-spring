package mx.app.petcare.dto;

import lombok.Data;

@Data
public class ReminderDto {

private long id;
	
	private String title;
	private String content;
	private boolean status;
	private PersonDto owner;
	private PetDto pet;
}
