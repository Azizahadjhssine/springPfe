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

import com.app.Dto.ParcoursDto;
import com.app.Services.ParcoursService;

@RestController
@RequestMapping("/api/v1/parcours")
@CrossOrigin(origins = "http://localhost:4200")
public class ParcoursController {
	@Autowired
	  private ParcoursService serParcours ;
	//  
	@PostMapping("/save")
	public ParcoursDto save(@RequestBody ParcoursDto dto) {
    	
		return serParcours.save(dto);
	}
	 @DeleteMapping("/delet/{id}")
		public void delete(@PathVariable Long id) {
			serParcours.delete(id);
		}
	 @GetMapping("/getbyid/{id}")
		public ParcoursDto findById(@PathVariable Long id) {
			return serParcours.findById(id);
		}
	 @GetMapping("/getall")
		public List<ParcoursDto> findAllParcours() {
			return serParcours.findAllParcours();
		}
}
