package mx.app.petcare.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.app.petcare.dto.PersonDto;
import mx.app.petcare.dto.PersonReadDto;
import mx.app.petcare.entity.Person;
import mx.app.petcare.service.PersonService;

@RestController
@RequestMapping("/petcare/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
    public ResponseEntity<PersonReadDto> findByUser(@PathVariable long id){        
		return personService.findByUser(id);
    }
	
	@PostMapping("/signup")
    public ResponseEntity<PersonReadDto> register(@Valid @RequestBody PersonDto personDto){
        
		return personService.save(convertToEntity(personDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<PersonReadDto> update(@Valid @RequestBody PersonDto personDto){        
		return personService.update(convertToEntity(personDto));
    }
	
	
	private Person convertToEntity(PersonDto personDto){
		return modelMapper.map(personDto, Person.class);
	}
}
