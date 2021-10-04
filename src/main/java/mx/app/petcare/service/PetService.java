package mx.app.petcare.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.PetReadDto;
import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Pet;
import mx.app.petcare.repository.PersonRepository;
import mx.app.petcare.repository.PetRepository;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<Page<PetReadDto>> findByPerson(long idOwner, Pageable pageable) {

		try {
			Person person = personRepository.getById(idOwner);
			Page<PetReadDto> petsDto = petRepository.findByOwner(person, pageable).map(pet -> convertToDto(pet));

			return new ResponseEntity<Page<PetReadDto>>(petsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Page<PetReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<PetReadDto> findOneById(long idPet) {
		try {
			return new ResponseEntity<PetReadDto>(convertToDto(petRepository.findById(idPet).get()),
					HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PetReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<PetReadDto> save(Pet pet) {
		try {		
			PetReadDto petDto = convertToDto(petRepository.save(pet));
			return new ResponseEntity<PetReadDto>(petDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<PetReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PetReadDto> update(Pet pet) {
		try {	
			
			PetReadDto petDto = convertToDto(petRepository.save(pet));
			return new ResponseEntity<PetReadDto>(petDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PetReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private PetReadDto convertToDto(Pet pet) {
		return modelMapper.map(pet, PetReadDto.class);
	}

}
