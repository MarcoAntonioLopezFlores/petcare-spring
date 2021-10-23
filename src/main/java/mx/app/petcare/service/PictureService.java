package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.PictureReadDto;
import mx.app.petcare.entity.Pet;
import mx.app.petcare.entity.Picture;
import mx.app.petcare.repository.PetRepository;
import mx.app.petcare.repository.PictureRepository;

@Service
public class PictureService {

	@Autowired
	private PictureRepository pictureRepository;
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<PictureReadDto>> findByPet(long idPet){

		try {
			Pet pet = petRepository.getById(idPet);		
			List<PictureReadDto> picturesDto = mapList(pictureRepository.findByPet(pet), PictureReadDto.class);

			return new ResponseEntity<List<PictureReadDto>>(picturesDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<PictureReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<PictureReadDto> findOneById(long idPicture){
		try {									
			return new ResponseEntity<PictureReadDto>(convertToDto(pictureRepository.findById(idPicture).get()), HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PictureReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PictureReadDto> save(Picture picture) {
		try {			
			PictureReadDto pictureDto = convertToDto(pictureRepository.save(picture));
			return new ResponseEntity<PictureReadDto>(pictureDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<PictureReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<PictureReadDto> update(Picture picture) {
		try {	
			
			PictureReadDto pictureDto = convertToDto(pictureRepository.save(picture));
			return new ResponseEntity<PictureReadDto>(pictureDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<PictureReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private PictureReadDto convertToDto(Picture picture) {
		return modelMapper.map(picture, PictureReadDto.class);
	}

	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

}
