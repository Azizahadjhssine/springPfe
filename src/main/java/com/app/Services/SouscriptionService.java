package com.app.Services;

import java.util.List;


import com.app.Dto.SouscriptionDto;

public interface SouscriptionService {
	    void delete(Long id);
	    
	    SouscriptionDto findById(Long id);
	    
	    List<SouscriptionDto> findAllSouscription();
	    
	    SouscriptionDto save(SouscriptionDto dto);
	    
		
		List<SouscriptionDto> listerSousbyParcours(String nameParcours);

}
