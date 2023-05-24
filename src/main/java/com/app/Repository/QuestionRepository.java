package com.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Long>{
	@Query("select q from Question q where q.etape.id like :im")
	 public List<Question> getQuestion(@Param("im") Long id);

}
