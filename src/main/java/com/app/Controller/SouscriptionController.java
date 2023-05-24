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

import com.app.Dto.SouscriptionDto;
import com.app.Services.SouscriptionService;

@RestController
@RequestMapping("/api/v1/Souscription")
public class SouscriptionController {
	
	@Autowired	
	private SouscriptionService serSous;
	@PostMapping("/save")
	public SouscriptionDto save(@RequestBody SouscriptionDto dto) {
    	
		return serSous.save(dto);
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id ) {
		serSous.delete(id);
	}
	@GetMapping("/findbyid/{id}")
	public SouscriptionDto findById(@PathVariable Long id) {
		return serSous.findById(id);
	}
	
	@GetMapping("/lister")
	public List<SouscriptionDto> findAllEmployees() {
		return serSous.findAllSouscription();
	}
	@GetMapping("/getSousByParcours/{nameParcours}")
	public List<SouscriptionDto> listerSousbyParcours(@PathVariable String nameParcours) {
		return serSous.listerSousbyParcours("%"+nameParcours+"%");
	}
}
