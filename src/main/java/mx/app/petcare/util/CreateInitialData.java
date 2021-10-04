package mx.app.petcare.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Role;
import mx.app.petcare.entity.Specie;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.PersonRepository;
import mx.app.petcare.repository.RoleRepository;
import mx.app.petcare.repository.SpecieRepository;
import mx.app.petcare.repository.UserRepository;

@Component
public class CreateInitialData implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpecieRepository specieRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		if (userRepository.findByEmail("marco@gmail.com") == null) {
			Role roleG = new Role();
			roleG.setName("ROLE_GENERAL");
			roleG.setDescription("GENERAL");						
			roleRepository.save(roleG);
			UserAccount userAccount = new UserAccount();
			
			Person person = new Person();
			person.setName("Marco");
			person.setLastname("Lopez");
			person.setPhone("7772501120");
			person.setAge(21);
			userAccount.setEmail("marco@gmail.com");
			userAccount.setPassword(passwordEncoder.encode("marco"));			
			userAccount.setRole(roleG);
			person.setUser(userAccount);
			
			personRepository.save(person);			
			
			
			if (specieRepository.findByName("PERRO") == null) {
				Specie specie = new Specie();
				specie.setName("PERROS");
				specie.setDescription("Especie canina");
				specieRepository.save(specie);
				specieRepository.save(new Specie(2,"GATOS","Especie gatuna"));
				specieRepository.save(new Specie(3,"AVES","Especie aviaria"));
			}
									
			if (roleRepository.findByName("ROLE_VET") == null) {
				Role roleV = new Role();
				roleV.setName("ROLE_VET");
				roleV.setDescription("VET");						
				roleRepository.save(roleV);					
			}
			
		}
	}
}
