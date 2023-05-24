package com.app.Dto;

import org.springframework.beans.BeanUtils;

import com.app.Entity.Assure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GestionAssureDto {
	//private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	private String dateNaissance;
	private String regime;
	
	private String civilite;
	private String etatCivil;
	
	private String codePostal;
	private String addresse;

	private String pays;
	private String ville;
	
	
	public static GestionAssureDto  fromEntity(Assure assure) {
		GestionAssureDto assureDto = new GestionAssureDto();
		BeanUtils.copyProperties(assure, assureDto);
		return assureDto;
	}
	
	public static Assure  toEntity(GestionAssureDto assuredto) {
		Assure assure = new Assure();
		BeanUtils.copyProperties(assuredto, assure);
		return assure;
	}
}
