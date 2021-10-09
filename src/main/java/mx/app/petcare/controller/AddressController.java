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

import mx.app.petcare.dto.AddressDto;
import mx.app.petcare.dto.AddressReadDto;
import mx.app.petcare.entity.Address;
import mx.app.petcare.service.AddressService;

@RestController
@RequestMapping("/petcare/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
    public ResponseEntity<AddressReadDto> findById(@PathVariable long id){        
		return addressService.findById(id);
    }
	
	@PostMapping("/")
    public ResponseEntity<AddressReadDto> create(@Valid @RequestBody AddressDto addressDto){        
		return addressService.save(convertToEntity(addressDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<AddressReadDto> update(@Valid @RequestBody AddressDto addressDto){        
		return addressService.update(convertToEntity(addressDto));
    }
	
	
	private Address convertToEntity(AddressDto addressDto){
		return modelMapper.map(addressDto, Address.class);
	}
}
