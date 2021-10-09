package mx.app.petcare.dto;

import lombok.Data;

@Data
public class PersonReadDto {

	private long id;
	private String name;
	private String lastname;
	private int age;
	private String phone;
	private AddressReadDto address;
	private UserReadDto user;
}
