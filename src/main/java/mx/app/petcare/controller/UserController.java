package mx.app.petcare.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.app.petcare.service.UserService;



@RestController
@RequestMapping("/petcare/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PutMapping("/{id}")
	public boolean updatePass(@PathVariable long id, @RequestBody Map<String, Object> information) {
		return userService.updatePass(id, information);
	}
	
	
}
