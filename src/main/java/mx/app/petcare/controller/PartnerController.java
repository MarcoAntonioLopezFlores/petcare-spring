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

import mx.app.petcare.dto.PartnerDto;
import mx.app.petcare.dto.PartnerReadDto;
import mx.app.petcare.entity.Partner;
import mx.app.petcare.service.PartnerService;

@RestController
@RequestMapping("/petcare/partner")
public class PartnerController {

	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
    public ResponseEntity<List<PartnerReadDto>> findAllByStatusFalse(){        
		return partnerService.findAllByStatusIsFalse();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<PartnerReadDto> findByUser(@PathVariable long id){        
		return partnerService.findByUser(id);
    }
	
	@PostMapping("/signup")
    public ResponseEntity<PartnerReadDto> register(@Valid @RequestBody PartnerDto partnerDto){
        
		return partnerService.save(convertToEntity(partnerDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<PartnerReadDto> update(@Valid @RequestBody PartnerDto partnerDto){        
		return partnerService.update(convertToEntity(partnerDto));
    }
	
	@PutMapping("/{id}")
    public boolean updateStatus(@PathVariable long id){        
		return partnerService.changeStatus(id);
    }
	
	
	private Partner convertToEntity(PartnerDto partnerDto){
		return modelMapper.map(partnerDto, Partner.class);
	}
}
