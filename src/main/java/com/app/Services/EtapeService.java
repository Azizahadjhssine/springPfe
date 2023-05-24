package com.app.Services;

import java.util.List;
import java.util.Optional;

import com.app.Dto.EtapeDto;

public interface EtapeService {
	void delete(Long id);
    EtapeDto findById(Long id);
    List<EtapeDto> findAllEtape();
    EtapeDto save(EtapeDto dto);
    List<EtapeDto> findByIdParcours(Long id);

}
