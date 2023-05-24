package com.app.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Dto.AssureDto;
import com.app.Dto.GestionAssureDto;
import com.app.Entity.Admin;
import com.app.Entity.Assure;
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
	public GestionAssureDto save(GestionAssureDto dto) {
		Assure assu = GestionAssureDto.toEntity(dto);
        
		assu.setRole(UserRole.ASSURE);
		assu.setPassword(passwordEncoder.encode(assu.getPassword()));
		Assure savedassu= assureRepository.save(assu); //admin: entity
        
        return GestionAssureDto.fromEntity(savedassu); // entity to D
	}

	@Override
	public void delete(Long id) {
		Assure assu = assureRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		assureRepository.deleteById(id);
		
	}

	@Override
	public AssureDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<AssureDto> findAllAssure() {
		return assureRepository.findAll()
		.stream()
		.map(AssureDto:: fromEntity)
		.collect(Collectors.toList());
	}

}
