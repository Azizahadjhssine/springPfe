package com.app.CompagnyService;

import com.app.Dto.RequestAlptisDto;

public interface AlptisService {

	     String regime(String r);
	     String getpriceAlptis(RequestAlptisDto ra);
	    
	 	//public void listeCombinaison(String debut, String[] suite, int fin) ;

	    
	    String tarifAlptis(String dateEffet,int departement,String dateAssure,String ragimeass,String env,String env1,String[] w);
	    String getTarifAlptis(RequestAlptisDto ra);

}
