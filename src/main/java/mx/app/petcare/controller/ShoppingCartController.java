package mx.app.petcare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.app.petcare.dto.ShoppingCartProductReadDto;
import mx.app.petcare.service.ShoppingCartService;

@RestController
@RequestMapping("/petcare/cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	

	@GetMapping("/user/{id}")
    public ResponseEntity<List<ShoppingCartProductReadDto>> findByPerson(@PathVariable long id){	    
		return shoppingCartService.findByPerson(id);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<List<ShoppingCartProductReadDto>> findByShoppingCart(@PathVariable long id){	    
		return shoppingCartService.findByShoppingCart(id);
    }
	
	@PostMapping("/product/{idProduct}/{idPerson}")
	public ResponseEntity<ShoppingCartProductReadDto> addProduct(@PathVariable long idProduct,@PathVariable long idPerson){
		return shoppingCartService.addProduct(idProduct, idPerson);
	}
	
	@DeleteMapping("/product/{idProduct}/{idPerson}")
	public boolean deleteProduct(@PathVariable long idProduct,@PathVariable long idPerson){
		return shoppingCartService.deleteProduct(idProduct, idPerson);
	}
	
	@DeleteMapping("/{id}")
	public boolean cleanShoppingCart(@PathVariable long id){
		return shoppingCartService.cleanShoppingCart(id);
	}
		
	
	

}
