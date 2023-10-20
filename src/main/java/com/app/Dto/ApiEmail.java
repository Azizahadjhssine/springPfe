package com.app.Dto;

import java.util.Set;

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
public class ApiEmail {
	private String nom;
	private String prenom;
	private String formule;
	private String email;
	private  String numTel;
	private  String dateNaissance;
	private String dateEffet;
	private  String garanties;
	private String name;
	private String price;
	private String regime;
	private String logo;

}
