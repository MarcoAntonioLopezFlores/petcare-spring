package mx.app.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.app.petcare.dto.SpecieReadDto;
import mx.app.petcare.service.SpecieService;

@RestController
@RequestMapping("/petcare/specie")
public class SpecieController {
	
	@Autowired
	private SpecieService specieService;
	
	@GetMapping("/")
	public ResponseEntity<List<SpecieReadDto>> findAll(){
		return specieService.findAll();
	}

}
