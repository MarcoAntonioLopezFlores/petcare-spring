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

import mx.app.petcare.dto.PictureDto;
import mx.app.petcare.dto.PictureReadDto;
import mx.app.petcare.entity.Picture;
import mx.app.petcare.service.PictureService;

@RestController
@RequestMapping("/petcare/picture")
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/pet/{id}")
    public ResponseEntity<List<PictureReadDto>> findByPet(@PathVariable long id){	    
		return pictureService.findByPet(id);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<PictureReadDto> findById(@PathVariable long id){
        
		return pictureService.findOneById(id);
    }
	
	@PostMapping("/")
    public ResponseEntity<PictureReadDto> create(@Valid @RequestBody PictureDto pictureDto){        
		return pictureService.save(convertToEntity(pictureDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<PictureReadDto> update(@Valid @RequestBody PictureDto pictureDto){        
		return pictureService.save(convertToEntity(pictureDto));
    }
	
	
	private Picture convertToEntity(PictureDto pictureDto){
		return modelMapper.map(pictureDto, Picture.class);
	}
}
