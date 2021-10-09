package mx.app.petcare.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleApiRequestException(Exception e){
		ApiException apiException = new ApiException("Ocurrio algun error", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
		
		return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {UsernameNotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiException handleUsernameNotFoundException(Exception e){
		return new ApiException("No se encontro el usuario", HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));
	}
}
