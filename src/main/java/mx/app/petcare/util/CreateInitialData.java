package mx.app.petcare.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mx.app.petcare.entity.Brand;
import mx.app.petcare.entity.Category;
import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Role;
import mx.app.petcare.entity.Specie;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.BrandRepository;
import mx.app.petcare.repository.CategoryRepository;
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
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepository;
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
			
			if (categoryRepository.findByName("Juguetes") == null) {
				
				categoryRepository.save(new Category(1,"Juguetes","Juguetes para mascotas",true));
				categoryRepository.save(new Category(2,"Comida","Comida para mascotas",true));
				categoryRepository.save(new Category(3,"Accesorios","Accesorios para mascotas",true));
				//Colocar más categorias
			}
			
			if (brandRepository.findByName("SuperPet") == null) {
				
				brandRepository.save(new Brand(1,"SuperPet","Marca para mascotas en general",true));
				brandRepository.save(new Brand(2,"Purina","Marca de alimentos para perros",true));
				brandRepository.save(new Brand(3,"Bayer","Marca de medicamentos para perros",true));
				brandRepository.save(new Brand(4,"Abene","Marca de alimentos para perros",true));
				brandRepository.save(new Brand(5,"Nupec","Marca de alimentos para perros",true));
				brandRepository.save(new Brand(6,"Whiskas","Marca de alimentos para gatos",true));
				brandRepository.save(new Brand(7,"Fancy Pets","Marca de accesorios para mascotas en general",true));
				//Colocar más marcas
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
