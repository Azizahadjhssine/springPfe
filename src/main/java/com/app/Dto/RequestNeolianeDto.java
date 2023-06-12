package com.app.Dto;

import java.util.Set;

import com.app.Entity.Enfant;

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
public class RequestNeolianeDto {
	private String nom;
	private String prenom;
	private String email;
	private String phone;
	private String mobile;
	private String codePostal;
	private String dateA;
	private String dateE;
    private String produit;
	private String regimeAssure;
	private ConjointDto conjoint;
	private Set<EnfantDto> enfants ;
	
}
