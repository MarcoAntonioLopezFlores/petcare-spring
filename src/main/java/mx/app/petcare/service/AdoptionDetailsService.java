package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

	public ResponseEntity<List<AdoptionDetailsReadDto>> findByAdopter(long idAdopter) {

		try {
			Person person = personRepository.getById(idAdopter);
			List<AdoptionDetailsReadDto> adoptionsDetailsDto = mapList(adoptionDetailsRepository.findByAdopter(person), AdoptionDetailsReadDto.class);

			return new ResponseEntity<List<AdoptionDetailsReadDto>>(adoptionsDetailsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<AdoptionDetailsReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<List<AdoptionDetailsReadDto>> findByOwner(long idOwner) {

		try {
			Person person = personRepository.getById(idOwner);
			List<AdoptionDetailsReadDto> adoptionsDetailsDto = mapList(adoptionDetailsRepository.findByOwner(person), AdoptionDetailsReadDto.class);

			return new ResponseEntity<List<AdoptionDetailsReadDto>>(adoptionsDetailsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<AdoptionDetailsReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

	private AdoptionDetailsReadDto convertToDto(AdoptionDetails adoptionDetails) {
		return modelMapper.map(adoptionDetails, AdoptionDetailsReadDto.class);
	}

}
