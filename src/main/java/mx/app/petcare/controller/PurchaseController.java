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

import mx.app.petcare.dto.PurchaseDto;
import mx.app.petcare.dto.PurchaseReadDto;
import mx.app.petcare.entity.Purchase;
import mx.app.petcare.service.PurchaseService;

@RestController
@RequestMapping("/petcare/purchase")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/user/{id}")
    public ResponseEntity<List<PurchaseReadDto>> findByPerson(@PathVariable long id){	    
		return purchaseService.findByPerson(id);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<PurchaseReadDto> findById(@PathVariable long id){
        
		return purchaseService.findOneById(id);
    }
	
	@PostMapping("/")
    public ResponseEntity<PurchaseReadDto> create(@Valid @RequestBody PurchaseDto purchaseDto){        
		return purchaseService.save(convertToEntity(purchaseDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<PurchaseReadDto> update(@Valid @RequestBody PurchaseDto purchaseDto){        
		return purchaseService.updateStatus(convertToEntity(purchaseDto));
    }
	
	
	private Purchase convertToEntity(PurchaseDto purchaseDto){
		return modelMapper.map(purchaseDto, Purchase.class);
	}
}
