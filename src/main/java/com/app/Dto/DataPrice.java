package com.app.Dto;

import java.util.Set;

import org.springframework.web.bind.annotation.RequestParam;

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
public class DataPrice {
			private  String PostCode;
		    private  String dateAssure;
		    private int codeproduit;
		    private  String nomProduit;
		    private  int comm;
		    private  String AssureRegime;
		    private String dateEffet;
			private ConjointDto conjoint;
			private Set<EnfantDto> enfant;

		
}
