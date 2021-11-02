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

import mx.app.petcare.dto.PictureProductDto;
import mx.app.petcare.dto.PictureReadDto;
import mx.app.petcare.entity.PictureProduct;
import mx.app.petcare.service.PictureProductService;


@RestController
@RequestMapping("/petcare/picture/product")
public class PictureProductController {

	@Autowired
	private PictureProductService pictureProductService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
    public ResponseEntity<List<PictureReadDto>> findByProduct(@PathVariable long id){	    
		return pictureProductService.findByProduct(id);
    }
	
	@PostMapping("/")
    public ResponseEntity<PictureReadDto> create(@Valid @RequestBody PictureProductDto pictureProductDto){        
		return pictureProductService.save(convertToEntity(pictureProductDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<PictureReadDto> update(@Valid @RequestBody PictureProductDto pictureProductDto){        
		return pictureProductService.save(convertToEntity(pictureProductDto));
    }
	
	
	private PictureProduct convertToEntity(PictureProductDto pictureProductDto){
		return modelMapper.map(pictureProductDto, PictureProduct.class);
	}
}
