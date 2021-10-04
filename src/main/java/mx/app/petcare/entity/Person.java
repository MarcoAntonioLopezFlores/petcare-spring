package mx.app.petcare.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table( uniqueConstraints = @UniqueConstraint(columnNames = "phone"))
public class Person {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private String name;
	private String lastname;
	private int age;
	private String phone;
	@OneToOne(cascade = CascadeType.ALL)
    private UserAccount user;
	
	
	
}
