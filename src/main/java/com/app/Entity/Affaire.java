package com.app.Entity;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Affaire {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String idaff;
private String nvProjet; //oui ou nn nouveau projet
private String statut;
private String origine;
//@JsonFormat(pattern="yyyy-MM-dd")
private String dateEffet;

//@JsonFormat(pattern="yyyy-MM-dd")
private String dateSignature;

@OneToOne
private Souscription souscription;


@ManyToOne
@JoinColumn(name="assure_id")
private Assure assure;

@ManyToOne(optional=true)
@JoinColumn(name="conjoint_id")
private Conjoint conjoint;


@ManyToMany(fetch = FetchType.EAGER)

@JoinTable(name = "Affaire_Enfant",
  joinColumns = @JoinColumn(name = "affaire_id"),
  inverseJoinColumns = @JoinColumn(name = "enfant_id"))
	private Set<Enfant> enfants;


/*public void addenfant(Enfant enfant) {
	if (this.enfants ==null ) {
		this.enfants = new HashSet<Enfant>();}
		this.enfants.add(enfant);
		
	if ( enfant.getAffaires()==null )
	{enfant.setAffaires(new HashSet<Affaire>());}
	 enfant.getAffaires().add(this);
}*/
}
