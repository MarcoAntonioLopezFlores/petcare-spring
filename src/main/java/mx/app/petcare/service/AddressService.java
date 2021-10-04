package mx.app.petcare.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.AddressReadDto;
import mx.app.petcare.entity.Address;
import mx.app.petcare.entity.Person;
import mx.app.petcare.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<AddressReadDto> findByPerson(long idAddress){
		try {
			Person person = new Person();
			person.setId(idAddress);			
			AddressReadDto addressDto = convertToDto(addressRepository.findByPerson(person));
			return new ResponseEntity<AddressReadDto>(addressDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<AddressReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<AddressReadDto> save(Address address) {
		try {
			AddressReadDto addressDto = convertToDto(addressRepository.save(address));
			return new ResponseEntity<AddressReadDto>(addressDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<AddressReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<AddressReadDto> update(Address address) {
		try {			
			
			AddressReadDto addressDto = convertToDto(addressRepository.save(address));
			return new ResponseEntity<AddressReadDto>(addressDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<AddressReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private AddressReadDto convertToDto(Address address) {
		return modelMapper.map(address, AddressReadDto.class);
	}
}
