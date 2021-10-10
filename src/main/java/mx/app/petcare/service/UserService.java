package mx.app.petcare.service;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean changeStatus(long id) {
		try {	
			UserAccount userAccount = userRepository.findById(id).get();
			userAccount.setEnabled(!userAccount.isEnabled());
			userAccount = userRepository.save(userAccount);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	public boolean updatePass(long idUser, Map<String, Object> informacion) {				
		try {
			UserAccount userAccount = userRepository.findById(idUser).get();
		
			if(passwordEncoder.matches(informacion.get("currentlyPass").toString(),userAccount.getPassword())) {
				userAccount.setPassword(passwordEncoder.encode(informacion.get("newPass").toString()));
				userRepository.save(userAccount);
				return true;
			}
			return false;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		
	}
	

}
