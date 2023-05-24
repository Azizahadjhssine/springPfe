package com.app.Dto;

import org.springframework.beans.BeanUtils;

import com.app.Entity.Parcours;

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
public class ParcoursDto {
	private Long id;
	private String name;
	private String desciption;
	
	public static ParcoursDto fromEntity(Parcours parcours) {
		ParcoursDto parcoursDto =new ParcoursDto();
		BeanUtils.copyProperties(parcours, parcoursDto);
		return parcoursDto;
	}
	
	public static Parcours toEntity(ParcoursDto request) {
		Parcours parcours = new Parcours();
		BeanUtils.copyProperties(request, parcours);
		return parcours;
	}

}
