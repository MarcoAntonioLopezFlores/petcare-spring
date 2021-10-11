package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import mx.app.petcare.dto.ProductReadDto;
import mx.app.petcare.entity.Partner;
import mx.app.petcare.entity.Product;
import mx.app.petcare.entity.Specie;
import mx.app.petcare.repository.PartnerRepository;
import mx.app.petcare.repository.ProductRepository;
import mx.app.petcare.repository.SpecieRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PartnerRepository partnerRepository;
	@Autowired
	private SpecieRepository specieRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<List<ProductReadDto>> findTopProducts(){
		try {			
			List<ProductReadDto> productsDto = mapList(productRepository.findTop10ByOrderByCreatedAtDescAndStatusIsTrue(), ProductReadDto.class);
			return new ResponseEntity<List<ProductReadDto>>(productsDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<List<ProductReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<ProductReadDto>> findAllByStatusIsTrue(){
		try {			
			List<ProductReadDto> productsDto = mapList(productRepository.findByStatusIsTrue(), ProductReadDto.class);
			return new ResponseEntity<List<ProductReadDto>>(productsDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<List<ProductReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<List<ProductReadDto>> findByNameContaining(String name) {

		try {
			
			List<ProductReadDto> productsDto = mapList(productRepository.findByNameIgnoreCaseContaining(name),ProductReadDto.class);

			return new ResponseEntity<List<ProductReadDto>>(productsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<ProductReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<List<ProductReadDto>> findBySpecie(long idSpecie) {

		try {
			Specie specie = specieRepository.getById(idSpecie);
			List<ProductReadDto> productsDto = mapList(productRepository.findBySpecieAndStatusIsTrue(specie),ProductReadDto.class);

			return new ResponseEntity<List<ProductReadDto>>(productsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<ProductReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<List<ProductReadDto>> findByPartner(long idPartner) {

		try {
			Partner partner = partnerRepository.getById(idPartner);
			List<ProductReadDto> productsDto = mapList(productRepository.findByPartner(partner),ProductReadDto.class);

			return new ResponseEntity<List<ProductReadDto>>(productsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<ProductReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<ProductReadDto> save(Product product) {
		try {
			
			ProductReadDto productDto = convertToDto(productRepository.save(product));
			return new ResponseEntity<ProductReadDto>(productDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<ProductReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<ProductReadDto> update(Product product) {
		try {			
			Product productFound = productRepository.findById(product.getId()).orElseThrow(() -> new NotFoundException("Product does not exist"));
			productFound.setName(product.getName());
			productFound.setDescription(product.getDescription());			
						
			ProductReadDto productDto = convertToDto(productRepository.save(productFound));
			return new ResponseEntity<ProductReadDto>(productDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<ProductReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public boolean changeStatus(long id){
		try {	
			Product productFound = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product does not exist"));
			productFound.setStatus(!productFound.isStatus());
			productRepository.save(productFound);
			return true;
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
	

	private ProductReadDto convertToDto(Product product) {
		return modelMapper.map(product, ProductReadDto.class);
	}
}
