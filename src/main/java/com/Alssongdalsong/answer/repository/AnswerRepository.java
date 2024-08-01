package com.Alssongdalsong.answer.repository;

import com.Alssongdalsong.answer.domain.Answer;
import com.Alssongdalsong.question.domain.QuestionNaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    Page<Answer> findAllByQuestionNaire(Pageable pageable, QuestionNaire questionNaire);
}
