package com.app.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.app.Dto.QuestionDto;
import com.app.Entity.Question;
import com.app.Repository.EtapeRepository;
import com.app.Repository.QuestionRepository;
import com.app.Services.QuestionService;
import com.app.Validations.ObjectsValidator;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QuestionImpl implements QuestionService{
	 private final QuestionRepository questionRepository;        // injection des dependences injecterun classe dans un autre
	    private final EtapeRepository etapeRepository;


	    private final   ObjectsValidator<QuestionDto> objectsValidator;


	   
	@Override
	public void delete(Long id) {
		 Question employee = questionRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		 questionRepository.deleteById(id);
		 }

	@Override
	public QuestionDto findById(Long id) {
		 return questionRepository.findById(id).map(QuestionDto::fromEntity)
	        		//map:  
	                .orElseThrow(() -> new EntityNotFoundException("No question was found with the provided ID"));
	   
	}


	

	@Override
	public QuestionDto save(QuestionDto dto) {
		objectsValidator.validate(dto);
        
        Question question = QuestionDto.toEntity(dto);
        
        Question savedQuestion= questionRepository.save(question); //employee: entity
        
        return QuestionDto.fromEntity(savedQuestion); 
	}

	@Override
	public List<QuestionDto> findAllQuestions() {
		 return questionRepository.findAll()
	                .stream() // parcour la liste 
	                .map(QuestionDto::fromEntity) // convertion liste 
	                .collect(Collectors.toList()); // retour liste
	}

	@Override
	public List<QuestionDto> findByIdEtape(Long id) {
		return questionRepository.getQuestion(id).stream().map(QuestionDto::fromEntity).collect(Collectors.toList());
		//		return etapeRepository.getEtape(id).stream().map(EtapeDto::fromEntity).collect(Collectors.toList());

	}

}
