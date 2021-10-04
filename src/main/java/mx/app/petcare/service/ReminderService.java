package mx.app.petcare.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public ResponseEntity<Page<ReminderReadDto>> findByPerson(long idOwner, Pageable pageable){

		try {
			Person person = personRepository.getById(idOwner);		
			Page<ReminderReadDto> remindersDto = reminderRepository.findByOwner(person, pageable).map(reminder -> convertToDto(reminder));

			return new ResponseEntity<Page<ReminderReadDto>>(remindersDto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Page<ReminderReadDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
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

}
