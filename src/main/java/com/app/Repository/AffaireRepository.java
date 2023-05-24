package com.app.Repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Dto.AffaireDto;
import com.app.Entity.Affaire;


public interface AffaireRepository extends JpaRepository<Affaire,Long>{
	/*@Query("select a from Affaire a where a.assure.id like %:z%")
	public List<AffaireDto> getAffairesByAssure(@Param("z") Long idassure);*/
	
	@Query("select e from Affaire e where e.assure.id like :im")
	 public List<Affaire> getAffairesByAssure(@Param("im") Long id);

}
