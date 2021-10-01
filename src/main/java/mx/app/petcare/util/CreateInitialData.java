package mx.app.petcare.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mx.app.petcare.entity.Role;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.RoleRepository;
import mx.app.petcare.repository.UserRepository;

@Component
public class CreateInitialData implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		if (userRepository.findByEmail("administrador@gmail.com") == null) {
			Role roleA = new Role();
			roleA.setName("ROLE_ADMIN");
			roleA.setDescription("ADMIN");
			roleRepository.save(roleA);
			UserAccount userAccount = new UserAccount();
			userAccount.setName("Marco");
			userAccount.setLastname("Lopez");
			userAccount.setPhone("7772501120");
			userAccount.setAge(21);
			userAccount.setEmail("administrador@gmail.com");
			userAccount.setPassword(passwordEncoder.encode("admin123"));			
			userAccount.setRole(roleA);
			userRepository.save(userAccount);			
			
					
									
			if (roleRepository.findByName("ROLE_VET") == null) {
				Role roleV = new Role();
				roleV.setName("ROLE_VET");
				roleV.setDescription("VET");						
				roleRepository.save(roleV);
				Role roleO = new Role();
				roleO.setName("ROLE_OWNER");
				roleO.setDescription("OWNER");						
				roleRepository.save(roleO);	
			}
			
		}
	}
}
