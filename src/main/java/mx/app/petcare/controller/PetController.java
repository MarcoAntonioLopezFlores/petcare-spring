package mx.app.petcare.controller;

import java.util.List;

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

import mx.app.petcare.dto.PetDto;
import mx.app.petcare.dto.PetReadDto;
import mx.app.petcare.entity.Pet;
import mx.app.petcare.service.PetService;

@RestController
@RequestMapping("/petcare/pet")
public class PetController {

	@Autowired
	private PetService petService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/user/{id}")
    public ResponseEntity<List<PetReadDto>> findByOwner(@PathVariable long id){	    
		return petService.findByPerson(id);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<PetReadDto> findById(@PathVariable long id){
        
		return petService.findOneById(id);
    }
	
	@PostMapping("/")
    public ResponseEntity<PetReadDto> create(@Valid @RequestBody PetDto petDto){        
		return petService.save(convertToEntity(petDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<PetReadDto> update(@Valid @RequestBody PetDto petDto){        
		return petService.save(convertToEntity(petDto));
    }
	
	
	private Pet convertToEntity(PetDto petDto){
		return modelMapper.map(petDto, Pet.class);
	}
}
