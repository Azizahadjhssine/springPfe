package com.app.Dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.app.Entity.Enfant;

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
public class EnfantDto {
	
	private long id;
	private String firstname;
	private String lastname;
	private String civilite;
	private String dateNaissance;
	private String regimeEnf;
	private String etatCivil;
	private String email;
	//private List<AffaireDto> affaireDto;
	
	public static EnfantDto fromEntity(Enfant enfant) {
		EnfantDto affDto= new EnfantDto ();
		BeanUtils.copyProperties(enfant, affDto);
		return affDto;
		
	}
	
   public static Enfant toEntity(EnfantDto request) {
		Enfant enfant = new Enfant();
		BeanUtils.copyProperties(request, enfant);
		return enfant;
		
	}

}
