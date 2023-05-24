package com.app.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Dto.AdminDto;
import com.app.Dto.GestionAdminDto;
import com.app.Entity.Admin;
import com.app.Repository.AdminRepository;
import com.app.Services.AdminService;
import com.app.Validations.ObjectsValidator;
import com.app.enums.UserRole;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminImpl implements AdminService{
	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;

    private final   ObjectsValidator<GestionAdminDto> objectsValidator;

	@Override
	public GestionAdminDto save(GestionAdminDto dto) {
		 //objectsValidator.validate(dto);
	       
	        
	        Admin adm = GestionAdminDto.toEntity(dto);
	        
	        adm.setRole(UserRole.ADMINISTRATEUR);
	        adm.setPassword(passwordEncoder.encode(adm.getPassword()));
	        Admin savedadm= adminRepository.save(adm); //admin: entity
	        
	        return GestionAdminDto.fromEntity(savedadm); // entity to D
	}

	@Override
	public void delete(Long id) {
		Admin parcours = adminRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
		adminRepository.deleteById(id);
		
	}

	@Override
	public AdminDto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdminDto> findAllAdmin() {
		return adminRepository.findAll()
				.stream()
				.map(AdminDto ::fromEntity)
				.collect(Collectors.toList());
	}

}
