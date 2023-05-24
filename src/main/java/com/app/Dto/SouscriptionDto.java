package com.app.Dto;

import org.springframework.beans.BeanUtils;

import com.app.Entity.Souscription;

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
public class SouscriptionDto {
	private long id;
	private String lastStep;
	private String data;
	
	private ParcoursDto parcoursDto;
	
	public static SouscriptionDto fromEntity(Souscription sous) {
		return SouscriptionDto.builder()
				.id(sous.getId())	
				.lastStep(sous.getLastStep())
				.data(sous.getData())
		        .parcoursDto(ParcoursDto.fromEntity(sous.getParcoursSous()))
		        .build();

	}
	
	public static Souscription toEntity(SouscriptionDto request) {
		Souscription souscription = new Souscription();
		BeanUtils.copyProperties(request, souscription);
		souscription.setParcoursSous(ParcoursDto.toEntity(request.getParcoursDto()));
		return souscription;
	}


}
