package com.app.Entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enfant {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_sequence")
	private long id;
	private String firstname;
	private String lastname;
	private String civilite;
	private String dateNaissance;
	private String regimeEnf;
	private String etatCivil;
	private String email;
	
	/*@ManyToMany(mappedBy = "enfants")
	private Set<Affaire> affaires;*/

}
