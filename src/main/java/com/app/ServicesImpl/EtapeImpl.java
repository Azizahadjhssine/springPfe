package com.app.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.app.Dto.EtapeDto;
import com.app.Entity.Etape;
import com.app.Repository.EtapeRepository;
import com.app.Services.EtapeService;
import com.app.Validations.ObjectsValidator;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EtapeImpl implements EtapeService{
	private final EtapeRepository etapeRepository;
    private final   ObjectsValidator<EtapeDto> objectsValidator;

	@Override
	public void delete(Long id) {
		Etape employee = etapeRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		etapeRepository.deleteById(id);		
	}

	@Override
	public EtapeDto findById(Long id) {
		 return etapeRepository.findById(id).map(EtapeDto::fromEntity)
	        		//map:  
	                .orElseThrow(() -> new EntityNotFoundException("No Etape was found with the provided ID"));
	  
	}

	@Override
	public List<EtapeDto> findAllEtape() {
		 return etapeRepository.findAll()
	                .stream() // parcour la liste 
	                .map(EtapeDto::fromEntity) // convertion liste 
	                .collect(Collectors.toList()); // retour liste
	}

	@Override
	public EtapeDto save(EtapeDto dto) {
		 objectsValidator.validate(dto);
	       
	        
	        Etape employee = EtapeDto.toEntity(dto);
	        
	        Etape savedEtape= etapeRepository.save(employee); //employee: entity
	        
	        return EtapeDto.fromEntity(savedEtape); // entity to D
	}

	@Override
	public List<EtapeDto> findByIdParcours(Long id) {
		return etapeRepository.getEtape(id).stream().map(EtapeDto::fromEntity).collect(Collectors.toList());
	  
	}

}
