package com.app.Services;

import java.util.List;

import com.app.Dto.ConjointDto;

public interface ConjointService {
	void delete(Long id);
	ConjointDto findById(Long id);
    List<ConjointDto> findAllConjoints();
    ConjointDto save(ConjointDto dto);

}
