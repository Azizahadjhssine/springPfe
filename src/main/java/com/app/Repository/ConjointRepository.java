package com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entity.Conjoint;

public interface ConjointRepository extends JpaRepository<Conjoint,Long> {
	@Query("select c from Conjoint c where c.firstname like %:x% and c.lastname like %:y% and c.dateNaissance like %:z%")
	public Conjoint getConjoint(@Param("x") String nom,@Param("y") String email ,@Param("z") String date);
}
