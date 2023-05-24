package com.app.Services;

import java.util.List;

import com.app.Dto.ParcoursDto;

public interface ParcoursService {
	 public ParcoursDto save(ParcoursDto dto);
	    void delete(Long id);
	    ParcoursDto findById(Long id);
	    List<ParcoursDto> findAllParcours();

}
