package com.app.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.app.Dto.EnfantDto;
import com.app.Dto.GestionAdminDto;
import com.app.Entity.Enfant;
import com.app.Repository.EnfantRepository;
import com.app.Services.EnfantService;
import com.app.Validations.ObjectsValidator;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EnfantImpl implements EnfantService{
	private final EnfantRepository enfantRepository;
    private final   ObjectsValidator<EnfantDto> objectsValidator;

	@Override
	public void delete(Long id) {
		Enfant enf =enfantRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
      enfantRepository.deleteById(id);	
	}

	@Override
	public EnfantDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnfantDto> findAllEnfants() {
		return enfantRepository.findAll()
				.stream()
				.map(EnfantDto::fromEntity)
				.collect(Collectors.toList());

	}

	@Override
	public EnfantDto save(EnfantDto dto) {
		 objectsValidator.validate(dto);
	        // controle  validation 
	       // Optional<Department> optionaldepartement =departementRepository.findById(id)  ;
	        
	        Enfant enf = EnfantDto.toEntity(dto);
	        
	        Enfant savedEnfant= enfantRepository.save(enf); //employee: entity
	        
	        return EnfantDto.fromEntity(savedEnfant); // ent
	}

	@Override
	public void addAffairetoEnfant(String nameAffaire, String nameEnfant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EnfantDto> listerenfantbyaffair(Long idAffaire) {
		// TODO Auto-generated method stub
		return null;
	}

}
