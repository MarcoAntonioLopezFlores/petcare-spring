package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.CreditCardReadDto;
import mx.app.petcare.entity.CreditCard;
import mx.app.petcare.entity.Person;
import mx.app.petcare.repository.CreditCardRepository;
import mx.app.petcare.repository.PersonRepository;


@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<CreditCardReadDto>> findByPerson(long idPerson) {

		try {
			Person person = personRepository.getById(idPerson);
			List<CreditCardReadDto> creditCardsDto = mapList(creditCardRepository.findByPerson(person),CreditCardReadDto.class);

			return new ResponseEntity<List<CreditCardReadDto>>(creditCardsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<CreditCardReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<CreditCardReadDto> findOneById(long idCreditCard) {
		try {
			return new ResponseEntity<CreditCardReadDto>(convertToDto(creditCardRepository.findById(idCreditCard).get()),
					HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<CreditCardReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<CreditCardReadDto> save(CreditCard creditCard) {
		try {		
			CreditCardReadDto creditCardDto = convertToDto(creditCardRepository.save(creditCard));
			return new ResponseEntity<CreditCardReadDto>(creditCardDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<CreditCardReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<CreditCardReadDto> update(CreditCard creditCard) {
		try {	
			
			CreditCardReadDto creditCardDto = convertToDto(creditCardRepository.save(creditCard));
			return new ResponseEntity<CreditCardReadDto>(creditCardDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<CreditCardReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

	private CreditCardReadDto convertToDto(CreditCard creditCard) {
		return modelMapper.map(creditCard, CreditCardReadDto.class);
	}

}
