package mx.app.petcare.dto;

import lombok.Data;
import mx.app.petcare.entity.Picture;


@Data
public class PetReadDto {

	private long id;	
	private String name;
	private String breed;
	private int age;
	private double growth;
	private String color;
	private String gender;
	private String temperament;
	private boolean isForAdopting;
	private boolean isAdopted;
	private SpecieReadDto specie;
	private PictureReadDto picture;
}
