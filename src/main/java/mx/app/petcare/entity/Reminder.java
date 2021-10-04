package mx.app.petcare.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Entity
public class Reminder {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	private String title;
	private String content;
	private boolean status;
	
	@CreatedDate
	private Date createdAt;
	
	@ManyToOne
	private Person owner;
	
	@ManyToOne
	private Pet pet;
}
