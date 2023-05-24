package com.app.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conjoint {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstname;
	private String lastname;
	private String civilite;
	private String dateNaissance;
	private String regimeConj;
	private String etatCivil;
	private String email;

	
	/*@OneToMany(mappedBy = "conjoint")
	private List<Affaire> affaires;*/

}
