package mx.app.petcare.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.app.petcare.dto.ReminderDto;
import mx.app.petcare.dto.ReminderReadDto;
import mx.app.petcare.entity.Reminder;
import mx.app.petcare.service.ReminderService;


@RestController
@RequestMapping("/petcare/reminder")
public class ReminderController {

	@Autowired
	private ReminderService reminderService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/user/{id}")
    public ResponseEntity<Page<ReminderReadDto>> findByOwner(@PathVariable long id, @PageableDefault(size = 5, page = 0) Pageable paging){	    
		return reminderService.findByPerson(id, paging);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<ReminderReadDto> findById(@PathVariable long id){
        
		return reminderService.findOneById(id);
    }
	
	@PostMapping("/")
    public ResponseEntity<ReminderReadDto> create(@Valid @RequestBody ReminderDto reminderDto){        
		return reminderService.save(convertToEntity(reminderDto));
    }
	
	@PutMapping("/")
    public ResponseEntity<ReminderReadDto> update(@Valid @RequestBody ReminderDto reminderDto){        
		return reminderService.save(convertToEntity(reminderDto));
    }
	
	
	private Reminder convertToEntity(ReminderDto reminderDto){
		return modelMapper.map(reminderDto, Reminder.class);
	}
}
