package mx.app.petcare.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table( uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAccount {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private String name;
	private String lastname;
	private int age;
	private String phone;
	private String email;
	private String password;
	
	private boolean enabled = true;
	
	@ManyToOne
    private Role role;

	
	
}
