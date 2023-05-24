package com.app.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Reponse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String id_champ;
	private String type;
	private String value;
	//question
	
	@OneToOne
	private Question question;


}
