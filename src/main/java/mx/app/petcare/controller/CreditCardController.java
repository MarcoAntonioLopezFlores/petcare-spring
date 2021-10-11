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

import mx.app.petcare.dto.CreditCardDto;
import mx.app.petcare.dto.CreditCardReadDto;
import mx.app.petcare.entity.CreditCard;
import mx.app.petcare.service.CreditCardService;


@RestController
@RequestMapping("/petcare/card")
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/user/{id}")
    public ResponseEntity<List<CreditCardReadDto>> findByPerson(@PathVariable long id){	    
		return creditCardService.findByPerson(id);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<CreditCardReadDto> findById(@PathVariable long id){
        
		return creditCardService.findOneById(id);
    }
	
	@PostMapping("/")
    public ResponseEntity<CreditCardReadDto> create(@Valid @RequestBody CreditCardDto creditCardDto){        
		return creditCardService.save(convertToEntity(creditCardDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<CreditCardReadDto> update(@Valid @RequestBody CreditCardDto creditCardDto){        
		return creditCardService.update(convertToEntity(creditCardDto));
    }
	
	
	private CreditCard convertToEntity(CreditCardDto creditCardDto){
		return modelMapper.map(creditCardDto, CreditCard.class);
	}

}
