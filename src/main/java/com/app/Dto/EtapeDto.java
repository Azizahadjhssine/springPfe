package com.app.Dto;


import com.app.Entity.Etape;

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
public class EtapeDto {
	
	private long id;
	private String libelle;
	private String explanation;
	
	private ParcoursDto parcoursDto;
	
	public static EtapeDto fromEntity(Etape etape) {
		return EtapeDto.builder()
				.id(etape.getId())
				.libelle(etape.getLibelle())
				.explanation(etape.getExplanation())
                .parcoursDto(ParcoursDto.fromEntity(etape.getParcours()))
                .build();
	}
	
	public static Etape toEntity(EtapeDto request) {
		return Etape.builder()
				.id(request.getId())
				.libelle(request.getLibelle())
				.explanation(request.getExplanation())
				.parcours(ParcoursDto.toEntity(request.getParcoursDto()))
				.build();
	}


}
