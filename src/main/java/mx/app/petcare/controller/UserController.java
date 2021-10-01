package mx.app.petcare.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.app.petcare.dto.UserCreateDto;
import mx.app.petcare.dto.UserReadDto;
import mx.app.petcare.entity.UserAccount;
import mx.app.petcare.service.UserService;


@RestController
@RequestMapping("/petcare/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("register")
    public ResponseEntity<UserReadDto> registrar(@Valid @RequestBody UserCreateDto userDto){
        
		return userService.save(convertToEntity(userDto));
    }
	
	private UserAccount convertToEntity(UserCreateDto userDto){
		return modelMapper.map(userDto, UserAccount.class);
	}
}
