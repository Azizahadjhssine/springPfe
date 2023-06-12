package com.app.ServicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Dto.AssureDto;
import com.app.Dto.ConjointDto;
import com.app.Dto.GestionAssureDto;
import com.app.Dto.ReponseDto;
import com.app.Entity.Admin;
import com.app.Entity.Assure;
import com.app.Entity.Reponse;
import com.app.Repository.AssureRepository;
import com.app.Services.AssureService;
import com.app.Validations.ObjectsValidator;
import com.app.enums.UserRole;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AssureImpl implements AssureService{
	private final AssureRepository assureRepository;
	private final PasswordEncoder passwordEncoder;

    private final   ObjectsValidator<GestionAssureDto> objectsValidator;

	@Override
	public AssureDto save(AssureDto dto) {
		Assure assu = AssureDto.toEntity(dto);
        
		assu.setRole(UserRole.ASSURE);
		assu.setPassword(passwordEncoder.encode(assu.getPassword()));
		Assure savedassu= assureRepository.save(assu); //admin: entity
        
        return AssureDto.fromEntity(savedassu); // entity to D
	}

	@Override
	public void delete(Long id) {
		Assure assu = assureRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		assureRepository.deleteById(id);
		
	}

	@Override
	public AssureDto findById(Long id) {
	
		return assureRepository.findById(id).map(AssureDto::fromEntity)
        		//map:  
                .orElseThrow(() -> new EntityNotFoundException("No CONJOINT  was found with the provided ID"));
	}

	
	@Override
	public List<AssureDto> findAllAssure() {
		return assureRepository.findAll()
		.stream()
		.map(AssureDto:: fromEntity)
		.collect(Collectors.toList());
	}

}
