package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entity.Enfant;

public interface EnfantRepository extends JpaRepository<Enfant,Long>{
	@Query("select c from Enfant c where c.firstname like %:x% and c.lastname like %:y% and c.dateNaissance like %:z%")
	Enfant getEnfant(@Param("x") String nom,@Param("y") String email ,@Param("z") String date);

}
