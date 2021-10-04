package mx.app.petcare.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import mx.app.petcare.entity.Role;

@Data
public class UserDto {

	private long id;
	
	@NotNull(message = "Email could not be null")
	private String email;
	@NotNull(message = "Pass could not be null")
	private String password;
	
	@NotNull(message = "Role could not be null")
	private Role role;
}
