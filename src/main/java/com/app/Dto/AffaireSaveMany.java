package com.app.Dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.app.Entity.Affaire;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AffaireSaveMany {

	private long id;
	private String idaff;
	private String nvProjet; //oui ou nn nouveau projet
	private String statut;
	private String origine;
	private String dateEffet;
	private String dateSignature;
	
	
	private Set<Long> enfantId;
	private SouscriptionDto sousDto;

	private AssureDto assureDto;
	private ConjointDto conjointDto;
	
	public static AffaireSaveMany fromEntity(Affaire affaire) {
		AffaireSaveMany affairedto = new AffaireSaveMany();
		BeanUtils.copyProperties(affaire, affairedto);
		return affairedto;
		
		}
	
	
	public static Affaire toEntity(AffaireSaveMany request){
			Affaire affaire = new Affaire();
			BeanUtils.copyProperties(request, affaire);
			return affaire;
		
		
	}
}
