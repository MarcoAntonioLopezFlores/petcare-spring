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

import mx.app.petcare.dto.AdoptionDetailsDto;
import mx.app.petcare.dto.AdoptionDetailsReadDto;
import mx.app.petcare.entity.AdoptionDetails;
import mx.app.petcare.service.AdoptionDetailsService;


@RestController
@RequestMapping("/adoptionDetailscare/adoption")
public class AdoptionDetailsController {

	@Autowired
	private AdoptionDetailsService adoptionDetailsService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/adopter/{id}")
    public ResponseEntity<List<AdoptionDetailsReadDto>> findByOwner(@PathVariable long id){	    
		return adoptionDetailsService.findByOwner(id);
    }
	
	@GetMapping("/owner/{id}")
    public ResponseEntity<List<AdoptionDetailsReadDto>> findByAdopter(@PathVariable long id){	    
		return adoptionDetailsService.findByAdopter(id);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<AdoptionDetailsReadDto> findById(@PathVariable long id){
        
		return adoptionDetailsService.findOneById(id);
    }
	
	@PostMapping("/")
    public ResponseEntity<AdoptionDetailsReadDto> create(@Valid @RequestBody AdoptionDetailsDto adoptionDetailsDto){        
		return adoptionDetailsService.save(convertToEntity(adoptionDetailsDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<AdoptionDetailsReadDto> update(@Valid @RequestBody AdoptionDetailsDto adoptionDetailsDto){        
		return adoptionDetailsService.save(convertToEntity(adoptionDetailsDto));
    }
	
	
	private AdoptionDetails convertToEntity(AdoptionDetailsDto adoptionDetailsDto){
		return modelMapper.map(adoptionDetailsDto, AdoptionDetails.class);
	}
}
