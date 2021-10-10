package mx.app.petcare.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import mx.app.petcare.dto.PersonReadDto;
import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<PersonReadDto> findByUser(Long id){
		try {
			UserAccount user = new UserAccount();
			user.setId(id);			
			PersonReadDto personDto = convertToDto(personRepository.findByUser(user));
			return new ResponseEntity<PersonReadDto>(personDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PersonReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PersonReadDto> save(Person person) {
		try {
			person.getUser().setPassword(passwordEncoder.encode(person.getUser().getPassword()));
			PersonReadDto personDto = convertToDto(personRepository.save(person));
			return new ResponseEntity<PersonReadDto>(personDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<PersonReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PersonReadDto> update(Person person) {
		try {			
			Person personFound = personRepository.findById(person.getId()).orElseThrow(() -> new NotFoundException("Person does not exist"));
			personFound.setName(person.getName());
			personFound.setLastname(person.getLastname());
			personFound.setPhone(person.getPhone());
			personFound.setAge(person.getAge());
			
			PersonReadDto personDto = convertToDto(personRepository.save(personFound));
			return new ResponseEntity<PersonReadDto>(personDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PersonReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

	private PersonReadDto convertToDto(Person person) {
		return modelMapper.map(person, PersonReadDto.class);
	}
}
