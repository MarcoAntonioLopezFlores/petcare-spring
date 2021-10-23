package mx.app.petcare.dto;

import lombok.Data;

@Data
public class PictureDto {

	private long id;
	private String fileType;
    private String name;
    private PetDto pet;
}
