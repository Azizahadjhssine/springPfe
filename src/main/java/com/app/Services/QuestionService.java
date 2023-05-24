package com.app.Services;

import java.util.List;


import com.app.Dto.QuestionDto;

public interface QuestionService {
	    void delete(Long id);
	    QuestionDto findById(Long id);
	    List<QuestionDto> findAllQuestions();
	    QuestionDto save(QuestionDto dto);
	    List<QuestionDto> findByIdEtape(Long id);

}
