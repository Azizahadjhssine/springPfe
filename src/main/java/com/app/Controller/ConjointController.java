package com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.ConjointDto;
import com.app.Services.ConjointService;
@RestController
@RequestMapping("/api/v1/Conjoint")
public class ConjointController {
	@Autowired
	ConjointService serviceConjoint;
	@PostMapping("/save")
	public ConjointDto save(@RequestBody ConjointDto dtomp) {
		
		return serviceConjoint.save(dtomp);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id ) {
		serviceConjoint.delete(id);
	}
	
	@GetMapping("/findbyid/{id}")
	public ConjointDto findById(@PathVariable Long id) {
		return serviceConjoint.findById(id);
	}
	
	@GetMapping("/lister")
	public List<ConjointDto> findAllEmployees() {
		return serviceConjoint.findAllConjoints();
	}
}
