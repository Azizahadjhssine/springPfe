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

import com.app.Dto.EtapeDto;
import com.app.Services.EtapeService;


@RestController
@RequestMapping("/api/v1/Etape")
@CrossOrigin(origins = "http://localhost:4200")

public class EtapeController {
	
	@Autowired
	EtapeService serviceEtape;
	
	@PostMapping("/save")
	public EtapeDto save(@RequestBody EtapeDto dtomp) {
		
		return serviceEtape.save(dtomp);
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id ) {
		serviceEtape.delete(id);
	}
	
	@GetMapping("/findbyid/{id}")
	public EtapeDto findById(@PathVariable Long id) {
		return serviceEtape.findById(id);
	}
	
	@GetMapping("/lister")
	public List<EtapeDto> findAllEmployees() {
		return serviceEtape.findAllEtape();
	}
	@GetMapping("/findbyidPrc/{id}")
	public List<EtapeDto> findByIdParcours(@PathVariable Long id) {
		return serviceEtape.findByIdParcours(id);
	}
}
