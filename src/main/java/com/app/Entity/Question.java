package com.app.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Question {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String category;
	private String description;
	private String value;

	/*@OneToOne(mappedBy = "question")
    private Reponse reponse;*/
	
	 @ManyToOne
	 @JoinColumn(name="id_etape")
	 private Etape etape;
	 
	 

}
