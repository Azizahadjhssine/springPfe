package com.app.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.app.Dto.ConjointDto;
import com.app.Entity.Conjoint;
import com.app.Repository.ConjointRepository;
import com.app.Services.ConjointService;
import com.app.Validations.ObjectsValidator;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ConjointImpl implements ConjointService{
	private final ConjointRepository conjRepository;
	
    private final   ObjectsValidator<ConjointDto> objectsValidator;

	@Override
	public void delete(Long id) {
		Conjoint employee = conjRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		conjRepository.deleteById(id);			
	}

	@Override
	public ConjointDto findById(Long id) {
		 return conjRepository.findById(id).map(ConjointDto::FromEntity)
	        		//map:  
	                .orElseThrow(() -> new EntityNotFoundException("No CONJOINT  was found with the provided ID"));
	  
	}

	@Override
	public List<ConjointDto> findAllConjoints() {
		return conjRepository.findAll()
                .stream() // parcour la liste 
                .map(ConjointDto::FromEntity) // convertion liste 
                .collect(Collectors.toList()); // retour liste
	}

	@Override
	public ConjointDto save(ConjointDto dto) {
		 objectsValidator.validate(dto);
	       
	        
	        Conjoint conjoint = ConjointDto.toEntity(dto);
	        
	        Conjoint savedEtape= conjRepository.save(conjoint); //employee: entity
	        
	        return ConjointDto.FromEntity(savedEtape); // entity to D
	}

}
