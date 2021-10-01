package mx.app.petcare.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import mx.app.petcare.entity.Role;

@Data
public class UserCreateDto {

	private long id;
	private String name;
	private String lastname;
	private int age;
	private String phone;
	
	@NotNull(message = "Email could not be null")
	private String email;
	@NotNull(message = "Pass could not be null")
	private String password;
	
	@NotNull(message = "Role could not be null")
	private Role role;
}
