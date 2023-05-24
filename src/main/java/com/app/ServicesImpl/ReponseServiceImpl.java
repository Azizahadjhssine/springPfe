package com.app.ServicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.app.Dto.ReponseDto;
import com.app.Entity.Question;
import com.app.Entity.Reponse;
import com.app.Repository.QuestionRepository;
import com.app.Repository.ReponseRepository;
import com.app.Services.ReponseService;
import com.app.Validations.ObjectsValidator;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReponseServiceImpl implements ReponseService{
	private final ReponseRepository repRepository;
	 private final   ObjectsValidator<ReponseDto> objectsValidator;
	 private final QuestionRepository questionRepository;

	@Override
	public ReponseDto save(ReponseDto dto, Long id) {
		objectsValidator.validate(dto);
        Question questid = questionRepository.findById(id).get(); 
		
		Reponse response= ReponseDto.toEntity(dto);
		response.setQuestion(questid);
		Reponse repsaved=repRepository.save(response);
		
		
		
		return ReponseDto.fromEntity(repsaved);
			}

	@Override
	public void delete(Long id) {
		Reponse rep = repRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		repRepository.deleteById(id);
		
	}

	@Override
	public ReponseDto findById(Long id) {
		return repRepository.findById(id).map(ReponseDto::fromEntity)
        		//map:  
                .orElseThrow(() -> new EntityNotFoundException("No reponse was found with the provided ID"));
	}

	

	@Override
	public List<ReponseDto> findAllReponse() {
		return repRepository.findAll()
                .stream() // parcour la liste 
                .map(ReponseDto::fromEntity) // convertion liste 
                .collect(Collectors.toList()); // retour liste
	}

	@Override
	public ReponseDto getReponseByQuest(Long id) {
		Reponse reponse = repRepository.getReponse(id);
	    if (reponse == null) {
	        throw new EntityNotFoundException("No reponse was found with the provided ID of question");
	    }
	    return ReponseDto.fromEntity(reponse);
	}

	

}
