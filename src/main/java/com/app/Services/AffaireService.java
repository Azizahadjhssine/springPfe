package com.app.Services;

import java.util.Date;

//import java.util.Date;
import java.util.List;
import java.util.Set;

import com.app.Dto.AffaireDto;
import com.app.Dto.AffaireSaveMany;
import com.app.Dto.AssureDto;
import com.app.Dto.ConjointDto;
import com.app.Dto.EnfantDto;
import com.app.Dto.SouscriptionDto;
import com.app.Entity.Affaire;
import com.app.exceptions.AffaireNotFoundExcep;


public interface AffaireService {

	AffaireDto save (AffaireDto affaire);
	void delete(Long id) throws AffaireNotFoundExcep;
    AffaireDto findById(Long id) throws AffaireNotFoundExcep;
    List<AffaireDto> findAllAffaire();
    void addAffaireToEnfant(String nameEmployee, String nameMission);
	AffaireSaveMany saveAffaire(AffaireSaveMany affaireDtoSavemany);
	
	//List<AffaireDto>affaireByAssure(Long id);/////////////////
	List<AffaireDto> listeraffaireByAssure(Long id);
	//	List<EnfantDto> listerEnfantByAffaire(Long id);


}
