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

import com.app.Dto.AssureDto;
import com.app.Dto.GestionAssureDto;
import com.app.Services.AssureService;

@RestController 
@RequestMapping("Api/v1/assure")
public class AssureController {

	
	@Autowired
	private AssureService assureService;
	
	@PostMapping("/save")
	public GestionAssureDto save(@RequestBody GestionAssureDto dtomp) {
		
		return assureService.save(dtomp);
	}
	
	/*@DeleteMapping("/delet/{id}")
	public void delete(@PathVariable Long id) {
		assureService.delete(id);
	}*/
	
	
	@DeleteMapping("/delet/{id}")
	public void delete(@PathVariable Long id) {
		assureService.delete(id);
	}
	
	@GetMapping("/getAllAssure")
	public List <AssureDto>findAllAssure(){
		return assureService.findAllAssure();
	}
}
