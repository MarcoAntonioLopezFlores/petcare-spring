package mx.app.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.app.petcare.dto.CategoryReadDto;
import mx.app.petcare.service.CategoryService;

@RestController
@RequestMapping("/petcare/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryReadDto>> findAll(){
		return categoryService.findAll();
	}

}

