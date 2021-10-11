package mx.app.petcare.dto;

import lombok.Data;

@Data
public class PersonDto {

	private long id;
	private String name;
	private String lastname;
	private String image;
	private int age;
	private String phone;
	private AddressDto address;
	private UserDto user;
}
