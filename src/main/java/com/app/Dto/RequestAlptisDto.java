package com.app.Dto;

import java.util.Set;

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

public class RequestAlptisDto {
		private int codePostal;
		private String dateAssure;
		private String nomproduit;
		private String regimeAssure;
		private String dateEffet;
		private ConjointDto Conjoint;
		private Set <EnfantDto>enfants;

		
}
