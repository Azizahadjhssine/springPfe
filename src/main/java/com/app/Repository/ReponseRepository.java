package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entity.Reponse;

public interface ReponseRepository extends JpaRepository<Reponse,Long>{
	@Query("select r from Reponse r where r.question.id like :im")
	Reponse getReponse(@Param("im") Long id);
	
}
