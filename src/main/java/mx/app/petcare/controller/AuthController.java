package mx.app.petcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.app.petcare.config.JwtResponse;
import mx.app.petcare.dto.AuthRequestDto;
import mx.app.petcare.service.AuthService;



@RestController
@RequestMapping("/petcare/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> createAuthentication(@RequestBody AuthRequestDto authRequestDto){
		return authService.createTokenAuthentication(authRequestDto);
	}

}
