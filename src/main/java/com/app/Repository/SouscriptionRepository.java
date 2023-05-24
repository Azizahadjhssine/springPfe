package com.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entity.Souscription;

public interface SouscriptionRepository extends JpaRepository<Souscription,Long>{
	@Query("select e from Souscription e where e.parcoursSous.name like :im")
	public List<Souscription> getSousByIdNameParcours(@Param("im") String name);
}
