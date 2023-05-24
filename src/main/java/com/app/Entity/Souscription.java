package com.app.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Souscription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String lastStep;
	private String data;
	
	//idAffaire
	@OneToOne(mappedBy = "souscription")
    private Affaire affaire;
	 @ManyToOne
	 @JoinColumn(name="id_parcours")
	 private Parcours parcoursSous;

}
