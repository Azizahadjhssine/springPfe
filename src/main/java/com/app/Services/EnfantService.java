package com.app.Services;

import java.util.List;

import com.app.Dto.EnfantDto;

public interface EnfantService {
	void delete(Long id);
	
    EnfantDto findById(Long id);
    
    List<EnfantDto> findAllEnfants();
    
    EnfantDto save(EnfantDto dto);
    
    
	void addAffairetoEnfant (String nameAffaire , String nameEnfant);
	List<EnfantDto> listerenfantbyaffair(Long idAffaire);
	
}
