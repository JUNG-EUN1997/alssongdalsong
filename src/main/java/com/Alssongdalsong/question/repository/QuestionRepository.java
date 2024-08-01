package com.Alssongdalsong.question.repository;

import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.question.domain.Question;
import com.Alssongdalsong.question.domain.QuestionNaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Page<Question> findAllByQuestionNaire(Pageable pageable, QuestionNaire questionNaire);
}
