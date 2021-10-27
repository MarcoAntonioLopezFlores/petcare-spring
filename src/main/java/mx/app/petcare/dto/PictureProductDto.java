package mx.app.petcare.dto;

import lombok.Data;

@Data
public class PictureProductDto {

	private long id;
	private String fileType;
    private String name;
	private ProductDto product;
}
