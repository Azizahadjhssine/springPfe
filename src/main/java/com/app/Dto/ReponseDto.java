package com.app.Dto;

import com.app.Entity.Question;
import com.app.Entity.Reponse;

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

public class ReponseDto {
	private long id;
	private String id_champ;
	private String type;
	private String value;
	//question
	
	private QuestionDto questionDto;
	
	
	public static ReponseDto fromEntity(Reponse reponse) {
		return ReponseDto.builder()
		.id(reponse.getId())	
		.id_champ(reponse.getId_champ())
		.type(reponse.getType())
		.value(reponse.getValue())
        .questionDto(QuestionDto.fromEntity(reponse.getQuestion()))
        .build();
}
	public static Reponse toEntity(ReponseDto dto) {
		return Reponse.builder()
		.id(dto.getId())	
		.id_champ(dto.getId_champ())
		.type(dto.getType())
		.value(dto.getValue())
        .question(QuestionDto.toEntity(dto.questionDto))
        .build();
		}
	
}