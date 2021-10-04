package mx.app.petcare.dto;

import lombok.Data;


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
}
