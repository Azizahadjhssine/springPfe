package com.app.Services;

import java.util.List;
import java.util.Optional;

import com.app.Dto.ReponseDto;

public interface ReponseService {
	ReponseDto save(ReponseDto dto, Long id);
	    void delete(Long id);
	    ReponseDto findById(Long id);
	    List<ReponseDto> findAllReponse();
	    ReponseDto getReponseByQuest(Long id);
}
