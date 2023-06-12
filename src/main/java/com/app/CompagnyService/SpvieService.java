package com.app.CompagnyService;

import java.util.Map;
import java.util.Set;

import com.app.Dto.ConjointDto;
import com.app.Dto.DataPrice;
import com.app.Dto.EnfantDto;
import com.app.Entity.Conjoint;

public interface SpvieService {
    String getprice(String PostCode,String dateAssure,String codeproduit,String nomproduit,String comm,
    		String AssurRegime,String dateEffet,ConjointDto conjoint,Set<EnfantDto> enfants);
    
    
    String regime(String r);
    String codePostal(String code, String regimeAssure);
    

    //test
    String getpriceTest(DataPrice dtp);
    
    
}
