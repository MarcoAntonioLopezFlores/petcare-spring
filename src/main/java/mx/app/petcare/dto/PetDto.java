package mx.app.petcare.dto;

import lombok.Data;
import mx.app.petcare.entity.Picture;
import mx.app.petcare.entity.Specie;

@Data
public class PetDto {

	private long id;	
	private String name;
	private String breed;
	private int age;
	private double growth;
	private String color;
	private String temperament;
	private String gender;
	private boolean isForAdopting;
	private boolean isAdopted;
	private Specie specie;
	private PersonDto owner;
	private PictureDto picture;
}
