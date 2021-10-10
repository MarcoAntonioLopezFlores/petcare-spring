package mx.app.petcare.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.app.petcare.dto.ReminderReadDto;
import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Reminder;
import mx.app.petcare.repository.PersonRepository;
import mx.app.petcare.repository.ReminderRepository;

@Service
public class ReminderService {
	
	@Autowired
	private ReminderRepository reminderRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<List<ReminderReadDto>> findByPerson(long idOwner){

		try {
			Person person = personRepository.getById(idOwner);		
			List<ReminderReadDto> remindersDto = mapList(reminderRepository.findByOwner(person), ReminderReadDto.class);

			return new ResponseEntity<List<ReminderReadDto>>(remindersDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<List<ReminderReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	public ResponseEntity<ReminderReadDto> findOneById(long idReminder){
		try {									
			return new ResponseEntity<ReminderReadDto>(convertToDto(reminderRepository.findById(idReminder).get()), HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<ReminderReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<ReminderReadDto> save(Reminder reminder) {
		try {			
			ReminderReadDto reminderDto = convertToDto(reminderRepository.save(reminder));
			return new ResponseEntity<ReminderReadDto>(reminderDto, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<ReminderReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<ReminderReadDto> update(Reminder reminder) {
		try {	
			
			ReminderReadDto reminderDto = convertToDto(reminderRepository.save(reminder));
			return new ResponseEntity<ReminderReadDto>(reminderDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return new ResponseEntity<ReminderReadDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private ReminderReadDto convertToDto(Reminder reminder) {
		return modelMapper.map(reminder, ReminderReadDto.class);
	}

	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

}
