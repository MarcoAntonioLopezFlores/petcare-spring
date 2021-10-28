package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.PictureReadDto;
import mx.app.petcare.entity.Product;
import mx.app.petcare.entity.PictureProduct;
import mx.app.petcare.repository.ProductRepository;
import mx.app.petcare.repository.PictureProductRepository;

@Service
public class PictureProductService {

	@Autowired
	private PictureProductRepository pictureProductRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<PictureReadDto>> findByProduct(long idProduct){

		try {
			Product product = productRepository.getById(idProduct);		
			List<PictureReadDto> pictureProductsDto = mapList(pictureProductRepository.findByProduct(product), PictureReadDto.class);

			return new ResponseEntity<List<PictureReadDto>>(pictureProductsDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<PictureReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<PictureReadDto> findOneById(long idPictureProduct){
		try {									
			return new ResponseEntity<PictureReadDto>(convertToDto(pictureProductRepository.findById(idPictureProduct).get()), HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PictureReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PictureReadDto> save(PictureProduct pictureProduct) {
		try {			
			PictureReadDto pictureProductDto = convertToDto(pictureProductRepository.save(pictureProduct));
			return new ResponseEntity<PictureReadDto>(pictureProductDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<PictureReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PictureReadDto> update(PictureProduct pictureProduct) {
		try {	
			
			PictureReadDto pictureProductDto = convertToDto(pictureProductRepository.save(pictureProduct));
			return new ResponseEntity<PictureReadDto>(pictureProductDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PictureReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private PictureReadDto convertToDto(PictureProduct pictureProduct) {
		return modelMapper.map(pictureProduct, PictureReadDto.class);
	}

	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

}

