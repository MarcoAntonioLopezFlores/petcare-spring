package mx.app.petcare.dto;

import lombok.Data;

@Data
public class PartnerReadDto {

	private long id;
	private String name;
	private String description;
	private byte[] file;
	private String phone;
	private String type;
	private String RFC;
	private boolean status;	
	private AddressReadDto address;
	private UserReadDto user;
}
