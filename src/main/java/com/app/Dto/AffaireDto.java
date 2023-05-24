package com.app.Dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;

import com.app.Entity.Affaire;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.app.Entity.Enfant;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffaireDto {
	private long id;
	private String idaff;
	private String nvProjet; //oui ou nn nouveau projet
	private String statut;
	private String origine;
	private String dateEffet;
	private String dateSignature;
	
	private SouscriptionDto souscriptionDto;       //oneToOne
	private AssureDto assureDto;                    //ManyToOne
	@Nullable
	private ConjointDto conjointDto;                   //ManyToOne
	
	private Set <EnfantDto>enfants;
	public boolean hasConjoint() {
        return conjointDto != null;
    }
	public static AffaireDto fromEntity(Affaire affaire) {
		
		AffaireDto affaireDtoo=new AffaireDto();
		affaireDtoo.setId(affaire.getId());
		affaireDtoo.setIdaff(affaire.getIdaff());

				affaireDtoo.setNvProjet(affaire.getNvProjet());
				affaireDtoo.setStatut(affaire.getStatut());
				affaireDtoo.setOrigine(affaire.getOrigine());
				affaireDtoo.setDateEffet(affaire.getDateEffet());
				affaireDtoo.setDateSignature(affaire.getDateSignature());
				affaireDtoo.setSouscriptionDto(SouscriptionDto.fromEntity(affaire.getSouscription()));

				affaireDtoo.setAssureDto(AssureDto.fromEntity(affaire.getAssure()));
			if(affaire.getConjoint()!=null) {
					affaireDtoo.setConjointDto(ConjointDto.FromEntity(affaire.getConjoint()));
				}else
					affaireDtoo.setConjointDto(null);
				if(affaire.getEnfants()!=null) {
				Set<EnfantDto> enfants = new HashSet();
				for(Enfant enf: affaire.getEnfants()) {
					EnfantDto enfantDto = EnfantDto.fromEntity(enf);
					enfants.add(enfantDto);
				}
				affaireDtoo.setEnfants(enfants);
				}else
					affaireDtoo.setEnfants(null);
				//affaireDto.setEnfants(EnfantDto.fromEntity(affaire.getEnfants()));
				return affaireDtoo;
				
				/*affaireDto = new AffaireDto();
		BeanUtils.copyProperties(affaire, affaireDto);
		affaireDto.setAssureDto(AssureDto.fromEntity(affaire.getAssure()));
		//if(affaire.getConjoint()!=null) {
			
		affaireDto.setConjointDto(ConjointDto.FromEntity(affaire.getConjoint()));
		//}
		affaireDto.setSouscriptionDto(SouscriptionDto.fromEntity(affaire.getSouscription()));
		
		
		Set<EnfantDto> enfants = new HashSet();
		for(Enfant enf: affaire.getEnfants()) {
			EnfantDto enfantDto = EnfantDto.fromEntity(enf);
			enfants.add(enfantDto);
		}
		affaireDto.setEnfants(enfants);
		
		//affaireDto.setEnfants(EnfantDto.fromEntity(affaire.getEnfants()));
		return affaireDto;*/

	}
	
	public static Affaire toEntity(AffaireDto request) {
		Affaire affaire=new Affaire();
		affaire.setId(request.getId());
		affaire.setIdaff(request.getIdaff());

		affaire.setNvProjet(request.getNvProjet());
		affaire.setStatut(request.getStatut());
				affaire.setOrigine(request.getOrigine());
				affaire.setDateEffet(request.getDateEffet());
				affaire.setDateSignature(request.getDateSignature());
				affaire.setSouscription(SouscriptionDto.toEntity(request.getSouscriptionDto()));

				affaire.setAssure(AssureDto.toEntity(request.getAssureDto()));
				if(request.getConjointDto()!=null) {
					affaire.setConjoint(ConjointDto.toEntity(request.getConjointDto()));
				}else
					affaire.setConjoint(null);
				
				
				if(request.getEnfants()!=null) {
				Set<Enfant> enfants = new HashSet();
				for(EnfantDto enf: request.getEnfants()) {
					Enfant enfant = EnfantDto.toEntity(enf);
					enfants.add(enfant);
				}
				affaire.setEnfants(enfants);
				}else
					affaire.setEnfants(null);
				//affaireDto.setEnfants(EnfantDto.fromEntity(affaire.getEnfants()));
				return affaire;
		
		/*Affaire affaire = new Affaire();
		BeanUtils.copyProperties(request, affaire);
		affaire.setAssure(AssureDto.toEntity(request.assureDto));
		affaire.setConjoint(ConjointDto.toEntity(request.conjointDto));
		affaire.setSouscription(SouscriptionDto.toEntity(request.getSouscriptionDto()));
		Set<Enfant> enfants = new HashSet();
		for(EnfantDto enfDto: request.getEnfants()) {
			Enfant enfant = EnfantDto.toEntity(enfDto);
			enfants.add(enfant);
		}
		
		affaire.setEnfants(enfants);
		System.out.print("dto "+ request.getEnfants().size());
		System.out.print("entity" + affaire.getEnfants().size());
		return affaire;*/
	}
	


}
