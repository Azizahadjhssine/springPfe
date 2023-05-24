package com.app.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@EqualsAndHashCode(callSuper = true)
public class Assure extends User{
	
	private String dateNaissance;
	private String regime;
	
	private String civilite;
	private String etatCivil;
	
	private String codePostal;
	private String addresse;

	private String pays;
	private String ville;
	
	
	@OneToMany(mappedBy = "assure")
	private List<Affaire> affaires;
}
