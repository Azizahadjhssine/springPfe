package com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.EnfantDto;
import com.app.Services.EnfantService;

@RestController 
@RequestMapping("Api/v1/enfant")
@CrossOrigin(origins = "http://localhost:4200")

public class EnfantController {
	
	@Autowired
	private EnfantService enfService;
	
	@PostMapping("/save")
	public EnfantDto save(@RequestBody EnfantDto dtomp) {
		
		return enfService.save(dtomp);
	}
	
	@GetMapping("/getall")
	public List <EnfantDto>getAllList(){
		return enfService.findAllEnfants();
	}
	
	@DeleteMapping("/delet/{id}")
	public void delet(@PathVariable Long id) {
		enfService.delete(id);
	}

}
