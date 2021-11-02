package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.BrandReadDto;
import mx.app.petcare.repository.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BrandRepository brandRepository;
	
	public ResponseEntity<List<BrandReadDto>> findAll(){
		return new ResponseEntity<List<BrandReadDto>>(mapList(brandRepository.findAll(), BrandReadDto.class), HttpStatus.ACCEPTED);
	}
	
	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

}
