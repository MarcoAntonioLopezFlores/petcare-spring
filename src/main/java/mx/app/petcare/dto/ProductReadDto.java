package mx.app.petcare.dto;

import lombok.Data;
import mx.app.petcare.entity.Brand;
import mx.app.petcare.entity.Category;

@Data
public class ProductReadDto {

	private long id;	
	private String name;
	private String description;
	private String image;
	private double price;
	private int quantityStock;  
	private PartnerReadDto partner;
    private Brand brand;
    private Category category;
    private SpecieReadDto specie;
    private PictureReadDto picture;
}
