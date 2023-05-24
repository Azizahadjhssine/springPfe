package com.app.Services;

import java.util.List;

import com.app.Dto.AssureDto;
import com.app.Dto.GestionAssureDto;

public interface AssureService {
	GestionAssureDto save(GestionAssureDto dto);
    void delete(Long id);
    AssureDto findById(Long id);
    List<AssureDto> findAllAssure();
}
