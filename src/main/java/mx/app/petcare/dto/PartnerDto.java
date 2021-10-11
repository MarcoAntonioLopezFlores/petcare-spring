package mx.app.petcare.dto;
import lombok.Data;

@Data
public class PartnerDto {

	private long id;
	
	private String name;
	private String description;
	private String file;
	private String phone;
	private String type;
	private String RFC;
	private boolean status;	
	private AddressDto address;
	private UserDto user;
}
