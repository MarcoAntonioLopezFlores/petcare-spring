package mx.app.petcare.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserReadDto {

	private long id;
	private String name;
	private String lastname;
	private int age;
	private String phone;
	private String email;
	
	@JsonIgnore
	private String role;
}
