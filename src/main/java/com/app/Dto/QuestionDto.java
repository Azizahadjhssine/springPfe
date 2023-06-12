package com.app.Dto;

import com.app.Entity.Question;

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
public class QuestionDto {
	
	
	private long id;
	private String category;
	private String description;
	private String value;
	private EtapeDto etapeDto;
	
	
	public static QuestionDto fromEntity(Question question) {
		return QuestionDto.builder()
		.id(question.getId())	
		.category(question.getCategory())
		.description(question.getDescription())
		.value(question.getValue())
		//.idChamp(question.getIdChamp())

        .etapeDto(EtapeDto.fromEntity(question.getEtape()))
        .build();

	}
	public static Question toEntity (QuestionDto request) {
		return Question.builder()
				.id(request.getId())
				.category(request.getCategory())
				.description(request.getDescription())
				.value(request.getValue())
				//.idChamp(request.getIdChamp())

				.etape(EtapeDto.toEntity(request.getEtapeDto()))
				.build();
		}

}
