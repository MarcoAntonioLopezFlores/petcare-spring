package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.SpecieReadDto;
import mx.app.petcare.repository.SpecieRepository;

@Service
public class SpecieService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SpecieRepository specieRepository;
	
	public ResponseEntity<List<SpecieReadDto>> findAll(){
		return new ResponseEntity<List<SpecieReadDto>>(mapList(specieRepository.findAll(), SpecieReadDto.class), HttpStatus.ACCEPTED);
	}
	
	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
	
}
