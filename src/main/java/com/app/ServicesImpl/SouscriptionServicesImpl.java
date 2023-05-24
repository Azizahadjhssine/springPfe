package com.app.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.app.Dto.SouscriptionDto;
import com.app.Entity.Souscription;
import com.app.Repository.SouscriptionRepository;
import com.app.Services.SouscriptionService;
import com.app.Validations.ObjectsValidator;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SouscriptionServicesImpl implements SouscriptionService{
	 private final SouscriptionRepository sousRepository;
		
		
	    private final   ObjectsValidator<SouscriptionDto> objectsValidator;
	    @Override
		public SouscriptionDto save(SouscriptionDto dto) {
			// TODO Auto-generated method stub
			 objectsValidator.validate(dto);
		        // controle  validation 
		       // Optional<Department> optionaldepartement =departementRepository.findById(id)  ;
		        
		        Souscription sous = SouscriptionDto.toEntity(dto);
		        
		        Souscription savedSous= sousRepository.save(sous); //employee: entity
		        
		        return SouscriptionDto.fromEntity(savedSous); 	}

	    
	@Override
	public void delete(Long id) {
		Souscription sous = sousRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		sousRepository.deleteById(id);		
	}

	@Override
	public SouscriptionDto findById(Long id) {
		return sousRepository.findById(id).map(SouscriptionDto::fromEntity)
        		//map:  
                .orElseThrow(() -> new EntityNotFoundException("No Employee was found with the provided ID"));
   
	}

	@Override
	public List<SouscriptionDto> findAllSouscription() {
		// TODO Auto-generated method stub
		return sousRepository.findAll().stream().map(SouscriptionDto::fromEntity) // convertion liste 
                .collect(Collectors.toList());
	}

	@Override
	public List<SouscriptionDto> listerSousbyParcours(String nameParcours) {
		List<Souscription> sous=sousRepository.getSousByIdNameParcours(nameParcours);
		List<SouscriptionDto> empdto=sous.stream().map(emp->SouscriptionDto.fromEntity(emp)).collect(Collectors.toList());
		return empdto;
	}
	

	
}
