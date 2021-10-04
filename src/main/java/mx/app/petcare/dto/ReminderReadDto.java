package mx.app.petcare.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReminderReadDto {

	private long id;
	private String title;
	private String content;
	private boolean status;
	private Date createdAt;	
	private PetReadDto pet;
}
