package com.Alssongdalsong.question.repository;

import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.question.domain.QuestionNaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionNaireRepository extends JpaRepository<QuestionNaire,Long> {
    Page<QuestionNaire> findAllByMember(Pageable pageable, Member member);
}
