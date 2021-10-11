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

import mx.app.petcare.dto.ProductDto;
import mx.app.petcare.dto.ProductReadDto;
import mx.app.petcare.entity.Product;
import mx.app.petcare.service.ProductService;

@RestController
@RequestMapping("/petcare/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
	public ResponseEntity<List<ProductReadDto>> findAllProductEnabled(){
		return productService.findAllByStatusIsTrue();
	}
	
	@GetMapping("/top")
	public ResponseEntity<List<ProductReadDto>> findTopProducts(){
		return productService.findTopProducts();
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<List<ProductReadDto>> findByProduct(@PathVariable long id){
		return productService.findByPartner(id);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<ProductReadDto>> findByNameContaining(@PathVariable String name){
		return productService.findByNameContaining(name);
	}
	
	@GetMapping("/specie/{id}")
	public ResponseEntity<List<ProductReadDto>> findBySpecie(@PathVariable long id){
		return productService.findBySpecie(id);
	}
	
	@PostMapping("/")
    public ResponseEntity<ProductReadDto> register(@Valid @RequestBody ProductDto productDto){
        
		return productService.save(convertToEntity(productDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<ProductReadDto> update(@Valid @RequestBody ProductDto productDto){        
		return productService.update(convertToEntity(productDto));
    }
	
	@PutMapping("/{id}")
    public boolean updateStatus(@PathVariable long id){        
		return productService.changeStatus(id);
    }
	
	private Product convertToEntity(ProductDto productDto){
		return modelMapper.map(productDto, Product.class);
	}
	
}
