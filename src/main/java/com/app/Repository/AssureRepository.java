package com.app.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entity.Assure;

public interface AssureRepository extends JpaRepository<Assure,Long>{
	@Query("select a from Assure a where a.firstname like %:x% and a.lastname like %:y% and a.dateNaissance like %:z%")

	Assure getAssure(@Param("x") String nom,@Param("y") String email ,@Param("z") String date);

	
	Assure findByEmail(String email);
}
