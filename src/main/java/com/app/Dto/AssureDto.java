package com.app.Dto;

import org.springframework.beans.BeanUtils;

import com.app.Entity.Assure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AssureDto  extends RegistrationRequest{
	
	private String dateNaissance;
	private String regime;
	
	private String civilite;
	private String etatCivil;
	
	private String codePostal;
	private String addresse;

	private String pays;
	private String ville;
	
	
	public static AssureDto  fromEntity(Assure assure) {
		 return AssureDto.builder()
			    	.id(assure.getId())
			        .firstname(assure.getFirstname())
			        .lastname(assure.getLastname())
			        .email(assure.getEmail())
			        .password(assure.getPassword())
			        .dateNaissance(assure.getDateNaissance())
			        .regime(assure.getRegime())
			        .civilite(assure.getCivilite())
			        .etatCivil(assure.getEtatCivil())
			        .codePostal(assure.getCodePostal())
			        .addresse(assure.getAddresse())
			        .pays(assure.getPays())
			        .ville(assure.getVille())
			        .build();
	}
	
	public static Assure toEntity(AssureDto request) {
	    return Assure.builder()
	    	.id(request.getId())
	        .firstname(request.getFirstname())
	        .lastname(request.getLastname())
	        .email(request.getEmail())
	        .password(request.getPassword())
	        .dateNaissance(request.getDateNaissance())
	        .regime(request.getRegime())
	        .civilite(request.getCivilite())
	        .etatCivil(request.getEtatCivil())
	        .codePostal(request.getCodePostal())
	        .addresse(request.getAddresse())
	        .pays(request.getPays())
	        .ville(request.ville)
	        .build();		
	  } 

}
