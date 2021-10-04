package mx.app.petcare.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.AdoptionDetailsReadDto;
import mx.app.petcare.entity.AdoptionDetails;
import mx.app.petcare.entity.Person;
import mx.app.petcare.repository.AdoptionDetailsRepository;
import mx.app.petcare.repository.PersonRepository;


@Service
public class AdoptionDetailsService {

	@Autowired
	private AdoptionDetailsRepository adoptionDetailsRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<Page<AdoptionDetailsReadDto>> findByAdopter(long idAdopter, Pageable pageable) {

		try {
			Person person = personRepository.getById(idAdopter);
			Page<AdoptionDetailsReadDto> adoptionsDetailsDto = adoptionDetailsRepository.findByAdopter(person, pageable).map(adoptionDetails -> convertToDto(adoptionDetails));

			return new ResponseEntity<Page<AdoptionDetailsReadDto>>(adoptionsDetailsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Page<AdoptionDetailsReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<Page<AdoptionDetailsReadDto>> findByOwner(long idOwner, Pageable pageable) {

		try {
			Person person = personRepository.getById(idOwner);
			Page<AdoptionDetailsReadDto> adoptionsDetailsDto = adoptionDetailsRepository.findByOwner(person, pageable).map(adoptionDetails -> convertToDto(adoptionDetails));

			return new ResponseEntity<Page<AdoptionDetailsReadDto>>(adoptionsDetailsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Page<AdoptionDetailsReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<AdoptionDetailsReadDto> findOneById(long idAdoptionDetails) {
		try {
			return new ResponseEntity<AdoptionDetailsReadDto>(convertToDto(adoptionDetailsRepository.findById(idAdoptionDetails).get()),
					HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<AdoptionDetailsReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<AdoptionDetailsReadDto> save(AdoptionDetails adoptionDetails) {
		try {		
			AdoptionDetailsReadDto adoptionDetailsDto = convertToDto(adoptionDetailsRepository.save(adoptionDetails));
			return new ResponseEntity<AdoptionDetailsReadDto>(adoptionDetailsDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<AdoptionDetailsReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<AdoptionDetailsReadDto> update(AdoptionDetails adoptionDetails) {
		try {	
			
			AdoptionDetailsReadDto adoptionDetailsDto = convertToDto(adoptionDetailsRepository.save(adoptionDetails));
			return new ResponseEntity<AdoptionDetailsReadDto>(adoptionDetailsDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<AdoptionDetailsReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private AdoptionDetailsReadDto convertToDto(AdoptionDetails adoptionDetails) {
		return modelMapper.map(adoptionDetails, AdoptionDetailsReadDto.class);
	}

}
