package com.app.Dto;

import javax.persistence.Entity;

import org.springframework.beans.BeanUtils;

import com.app.Entity.Conjoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConjointDto {
	private long id;
	private String firstname;
	private String lastname;
	private String civilite;
	private String dateNaissance;
	private String regimeConj;
	private String etatCivil;
	private String email;
	
	public static ConjointDto FromEntity(Conjoint conjoint) {
		ConjointDto conjointDto = new ConjointDto();
		BeanUtils.copyProperties(conjoint, conjointDto);
		return conjointDto;
	}
	
	public static Conjoint toEntity(ConjointDto request) {
		Conjoint conjoint = new Conjoint ();
		BeanUtils.copyProperties(request, conjoint);
		return conjoint;
	}

}
