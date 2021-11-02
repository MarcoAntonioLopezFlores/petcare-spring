package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.ShoppingCartProductReadDto;
import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Product;
import mx.app.petcare.entity.ShoppingCart;
import mx.app.petcare.entity.ShoppingCartProduct;
import mx.app.petcare.repository.PersonRepository;
import mx.app.petcare.repository.ProductRepository;
import mx.app.petcare.repository.ShoppingCartProductRepository;
import mx.app.petcare.repository.ShoppingCartRepository;


@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	@Autowired
	private ShoppingCartProductRepository shoppingCartProductRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<ShoppingCartProductReadDto>> findByPerson(long idPerson){
		try {
			Person person = personRepository.getById(idPerson);
			ShoppingCart shoppingCart = shoppingCartRepository.findByPersonAndStatusIsTrue(person);
			List<ShoppingCartProductReadDto> shoppingCartProductsDto = mapList(shoppingCartProductRepository.findByShoppingCart(shoppingCart),ShoppingCartProductReadDto.class); 
			
			return new ResponseEntity<List<ShoppingCartProductReadDto>>(shoppingCartProductsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<ShoppingCartProductReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<ShoppingCartProductReadDto>> findByShoppingCart(long idShoppingCart){
		try {
			ShoppingCart shoppingCart = shoppingCartRepository.getById(idShoppingCart);
			List<ShoppingCartProductReadDto> shoppingCartProductsDto = mapList(shoppingCartProductRepository.findByShoppingCart(shoppingCart),ShoppingCartProductReadDto.class); 
			
			return new ResponseEntity<List<ShoppingCartProductReadDto>>(shoppingCartProductsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<ShoppingCartProductReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<ShoppingCartProductReadDto> addProduct(long idProduct,long idPerson) {
		try {		
			Product product = productRepository.findByIdAndStatusIsTrue(idProduct);
			if(product.getQuantityStock()>0) {
				Person person = personRepository.getById(idPerson);
				ShoppingCart shoppingCart = shoppingCartRepository.findByPersonAndStatusIsTrue(person);
				if(shoppingCart==null) {
					shoppingCart = new ShoppingCart();
					shoppingCart.setPerson(person);					
					shoppingCart = shoppingCartRepository.save(shoppingCart);
				}
				ShoppingCartProduct shoppingCartProduct = shoppingCartProductRepository.findByShoppingCartAndProduct(shoppingCart, product);							
                if(shoppingCartProduct!=null){
                	shoppingCartProduct.setQuantity(shoppingCartProduct.getQuantity()+1);
                	shoppingCartProduct.setSubtotal(shoppingCartProduct.getQuantity()*product.getPrice());                    
                    shoppingCartProduct= shoppingCartProductRepository.save(shoppingCartProduct);                    
                }else{
                	shoppingCartProduct = new ShoppingCartProduct();
                	shoppingCartProduct.setProduct(product);
                	shoppingCartProduct.setQuantity(1);
                	shoppingCartProduct.setShoppingCart(shoppingCart);
                	shoppingCartProduct.setSubtotal(product.getPrice());
                    shoppingCartProduct = shoppingCartProductRepository.save(shoppingCartProduct);                    
                }
                product.setQuantityStock(product.getQuantityStock()-1);
                product =productRepository.save(product);
                if(product.getQuantityStock()==0){
                    product.setStatus(false);
                    productRepository.save(product);
                }
                shoppingCart.setTotal(shoppingCart.getTotal()+product.getPrice());
                shoppingCartRepository.save(shoppingCart);
                
                ShoppingCartProductReadDto shoppingCartProductDto = convertToDto(shoppingCartProduct);			
    			return new ResponseEntity<ShoppingCartProductReadDto>(shoppingCartProductDto, HttpStatus.CREATED);
			}			
		} catch (Exception e) {
			return new ResponseEntity<ShoppingCartProductReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ShoppingCartProductReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public boolean deleteProduct(long idProduct,long idPerson){
		try {
			Product product = productRepository.findByIdAndStatusIsTrue(idProduct);
			Person person = personRepository.getById(idPerson);
			ShoppingCart shoppingCart= shoppingCartRepository.findByPersonAndStatusIsTrue(person);
            ShoppingCartProduct shoppingCartProduct = shoppingCartProductRepository.findByShoppingCartAndProduct(shoppingCart, product);
            if(shoppingCartProduct!=null){
                product.setQuantityStock(product.getQuantityStock()+shoppingCartProduct.getQuantity());
                shoppingCart.setTotal(shoppingCart.getTotal() - (shoppingCartProduct.getSubtotal()));
                productRepository.save(product);
                shoppingCartRepository.save(shoppingCart);
                shoppingCartProductRepository.delete(shoppingCartProduct);
                return true;
            }
            return false;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean cleanShoppingCart(long idShoppingCart){
		try {
			ShoppingCart shoppingCart = shoppingCartRepository.getById(idShoppingCart);
			List<ShoppingCartProduct> shoppingCartProducts = shoppingCartProductRepository.findByShoppingCart(shoppingCart);
			shoppingCartProducts.forEach(shoppingCartProduct->{
				Product product = productRepository.getById(shoppingCartProduct.getProduct().getId());
				product.setQuantityStock(product.getQuantityStock()+shoppingCartProduct.getQuantity());
				product.setStatus(true);
				productRepository.save(product);
				shoppingCartProductRepository.delete(shoppingCartProduct);
			});
			shoppingCartRepository.delete(shoppingCart);
            return !shoppingCartRepository.existsById(shoppingCart.getId());
		} catch (Exception e) {
			return false;
		}
		
	}

	
	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

	private ShoppingCartProductReadDto convertToDto(ShoppingCartProduct shoppingCartProduct) {
		return modelMapper.map(shoppingCartProduct, ShoppingCartProductReadDto.class);
	}

}
