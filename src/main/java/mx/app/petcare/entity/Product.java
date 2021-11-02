package mx.app.petcare.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
@Data
@Entity
public class Product {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	private String name;
	private String description;
	
	private String image;
	private double price;
	
	private Date createdAt = new Date();

	private int quantityStock;

	private boolean status = true;
    
	@ManyToOne
	private Partner partner;
    
    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Category category;
    
    @ManyToOne
    private Specie specie;
    
    @OneToOne
    private PictureProduct picture;
}
