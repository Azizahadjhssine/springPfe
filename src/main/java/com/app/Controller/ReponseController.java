package com.app.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.ReponseDto;
import com.app.Services.ReponseService;


@RestController 
@RequestMapping("Api/v1/Reponse")
public class ReponseController {
	@Autowired
	 ReponseService servicerepo;
	@PostMapping("/save/{id}")
	public ResponseEntity<ReponseDto> save(@RequestBody ReponseDto dto, @PathVariable Long id) {
		ReponseDto repdtosaved = servicerepo.save(dto, id);

		return new ResponseEntity<ReponseDto>(repdtosaved, HttpStatus.CREATED);
	}
	

@DeleteMapping("/delete/{id}")
public void delete(@PathVariable Long id ) {
	servicerepo.delete(id);
}

@GetMapping("/findbyid/{id}")
public ReponseDto findById(@PathVariable Long id) {
	return servicerepo.findById(id);
}


@GetMapping("/lister")
public List<ReponseDto> findAllEmployees() {
	return servicerepo.findAllReponse();
}
@GetMapping("/findbyidQuestion/{id}")
public ReponseDto findByIdquest(@PathVariable Long id) {
	return servicerepo.getReponseByQuest(id);
}	
	
}
