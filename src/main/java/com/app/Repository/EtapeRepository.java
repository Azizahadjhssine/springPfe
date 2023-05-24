package com.app.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.app.Entity.Etape;

public interface EtapeRepository extends JpaRepository<Etape,Long>{
	
	
	@Query("select e from Etape e where e.parcours.id like :im")
	 public List<Etape> getEtape(@Param("im") Long id);
}
