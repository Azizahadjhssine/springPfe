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

import com.app.Dto.QuestionDto;
import com.app.Services.QuestionService;



@RestController
@RequestMapping("/api/v1/question")
@CrossOrigin(origins = "http://localhost:4200")

public class QuestionController {
	
	@Autowired
	QuestionService servicequest;
	@PostMapping("/save")
	public QuestionDto save(@RequestBody QuestionDto dtomp) {
		
		return servicequest.save(dtomp);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id ) {
		servicequest.delete(id);
	}

	@GetMapping("/findbyid/{id}")
	public QuestionDto findById(@PathVariable Long id) {
		return servicequest.findById(id);
	}


	@GetMapping("/lister")
	public List<QuestionDto> findAllEmployees() {
		return servicequest.findAllQuestions();
	}
	
	@GetMapping("/findbyidetape/{id}")
	public List<QuestionDto> findByIdParcours(@PathVariable Long id) {
		return servicequest.findByIdEtape(id);
	}
}
