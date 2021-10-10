package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.PurchaseReadDto;
import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Purchase;
import mx.app.petcare.entity.ShoppingCart;
import mx.app.petcare.repository.PersonRepository;
import mx.app.petcare.repository.PurchaseRepository;
import mx.app.petcare.repository.ShoppingCartRepository;


@Service
public class PurchaseService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<PurchaseReadDto>> findByPerson(long idPerson) {

		try {
			Person person = personRepository.getById(idPerson);
			List<PurchaseReadDto> purchasesDto = mapList(purchaseRepository.findByPersonOrderByCreatedAtDesc(person),PurchaseReadDto.class);

			return new ResponseEntity<List<PurchaseReadDto>>(purchasesDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<PurchaseReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<PurchaseReadDto> findOneById(long idPurchase) {
		try {
			return new ResponseEntity<PurchaseReadDto>(convertToDto(purchaseRepository.findById(idPurchase).get()),
					HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PurchaseReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<PurchaseReadDto> save(Purchase purchase) {
		try {	
			
			PurchaseReadDto purchaseDto = convertToDto(purchaseRepository.save(purchase));
			ShoppingCart cart = shoppingCartRepository.getById(purchase.getShoppingCart().getId());
			cart.setStatus(false);
			shoppingCartRepository.save(cart);
			return new ResponseEntity<PurchaseReadDto>(purchaseDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<PurchaseReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<PurchaseReadDto> updateStatus(Purchase purchase) {
		try {	
			Purchase purchaseFound = purchaseRepository.getById(purchase.getId());
			purchaseFound.setStatus(purchase.getStatus());
			PurchaseReadDto purchaseDto = convertToDto(purchaseRepository.save(purchase));
			return new ResponseEntity<PurchaseReadDto>(purchaseDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PurchaseReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

	private PurchaseReadDto convertToDto(Purchase purchase) {
		return modelMapper.map(purchase, PurchaseReadDto.class);
	}

}
