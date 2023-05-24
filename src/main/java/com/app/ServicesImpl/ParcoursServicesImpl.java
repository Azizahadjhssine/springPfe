package com.app.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Dto.ParcoursDto;
import com.app.Entity.Parcours;
import com.app.Repository.ParcoursRepository;
import com.app.Services.ParcoursService;
import com.app.Validations.ObjectsValidator;


@Service
public class ParcoursServicesImpl implements ParcoursService{
	
@Autowired 
ParcoursRepository parcoursRepository;
@Autowired
ObjectsValidator<ParcoursDto> x;

	@Override
	public ParcoursDto save(ParcoursDto dto) {
		// TODO Auto-generated method stub
		
				//x.validate(dto);
				Parcours parcours= ParcoursDto.toEntity(dto);
				
				Parcours parcourssd = parcoursRepository.save(parcours);
				
				
				return ParcoursDto.fromEntity(parcourssd);	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Parcours parcours = parcoursRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		parcoursRepository.deleteById(id);
		
	}

	@Override
	public ParcoursDto findById(Long id) {
		// TODO Auto-generated method stub+-+
				return parcoursRepository.findById(id).map(ParcoursDto :: fromEntity)
		        		//map: 
		                .orElseThrow(() -> new EntityNotFoundException("No parcours was found with the provided ID"));
	}

	@Override
	public List<ParcoursDto> findAllParcours() {
		// TODO Auto-generated method stub
		 return parcoursRepository.findAll()
	                .stream() // parcour la liste 
	                .map(ParcoursDto :: fromEntity) // convertion liste 
	                .collect(Collectors.toList());
		
	}

}
