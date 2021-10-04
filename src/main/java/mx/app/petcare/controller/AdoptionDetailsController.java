package mx.app.petcare.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<AdoptionDetailsReadDto>> findByOwner(@PathVariable long id, @PageableDefault(size = 5, page = 0) Pageable paging){	    
		return adoptionDetailsService.findByOwner(id, paging);
    }
	
	@GetMapping("/owner/{id}")
    public ResponseEntity<Page<AdoptionDetailsReadDto>> findByAdopter(@PathVariable long id, @PageableDefault(size = 5, page = 0) Pageable paging){	    
		return adoptionDetailsService.findByAdopter(id, paging);
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
