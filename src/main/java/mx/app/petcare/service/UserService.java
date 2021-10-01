package mx.app.petcare.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.UserReadDto;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.UserRepository;

@Service
public class UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	
	public boolean disabled(long id) {
		UserAccount userAccount =userRepository.findById(id).get();
		userAccount.setEnabled(false);
		userAccount = userRepository.save(userAccount);
		return !userAccount.isEnabled();
		
	}
	
	public boolean enabled(long id) {
		UserAccount userAccount =userRepository.findById(id).get();
		userAccount.setEnabled(true);
		
		userAccount = userRepository.save(userAccount);
		
		return userAccount.isEnabled();
		
	}
	
	public boolean changePassword(String email,String newPassword) {
		try{
			UserAccount userAccount = userRepository.findByEmail(email);
			userAccount.setPassword(passwordEncoder.encode(newPassword));
			
			userRepository.save(userAccount);
			return true;
		}catch(Exception e) {
			return false;
		}	
	}
	

	public ResponseEntity<UserReadDto> save(UserAccount userAccount) {
		try {			
			userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
			UserReadDto user = convertToDto(userRepository.save(userAccount));
			return new ResponseEntity<UserReadDto>(user,HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<UserReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	private UserReadDto convertToDto(UserAccount user){
		return modelMapper.map(user, UserReadDto.class);
	}

	}
