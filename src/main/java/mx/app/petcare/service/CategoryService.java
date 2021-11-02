package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.CategoryReadDto;
import mx.app.petcare.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ResponseEntity<List<CategoryReadDto>> findAll(){
		return new ResponseEntity<List<CategoryReadDto>>(mapList(categoryRepository.findAll(), CategoryReadDto.class), HttpStatus.ACCEPTED);
	}
	
	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

}
