package com.Alssongdalsong.question.repository;

import com.Alssongdalsong.question.domain.QuestionNaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionNaireRepository extends JpaRepository<QuestionNaire,Long> {
}
