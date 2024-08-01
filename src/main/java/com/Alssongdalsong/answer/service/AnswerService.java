package com.Alssongdalsong.answer.service;

import com.Alssongdalsong.answer.domain.Answer;
import com.Alssongdalsong.answer.dto.AnswerListResDto;
import com.Alssongdalsong.answer.dto.AnswerSaveQuestionReqDto;
import com.Alssongdalsong.answer.repository.AnswerRepository;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.service.MemberService;
import com.Alssongdalsong.question.domain.Question;
import com.Alssongdalsong.question.domain.QuestionNaire;
import com.Alssongdalsong.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final QuestionService questionService;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, MemberService memberService, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.memberService = memberService;
        this.questionService = questionService;
    }

    public Answer answerCreatePerQuestion(AnswerSaveQuestionReqDto dto, Long questionId){
        String memberEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.findByEmail(memberEmail);
        QuestionNaire questionNaire = questionService.findQuestionNaireByIdRequired(dto.getQuestionNaireId());
        Question question = questionService.findQuestionByIdRequired(dto.getQuestionId());


        Answer answer = dto.toEntity(member, questionNaire, question);
        answerRepository.save(answer);
        return answer;
    }

    public Page<AnswerListResDto> answerList(Pageable pageable, Long questionNaireId){
//        String memberEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        Member member = memberService.findByEmail(memberEmail);

        QuestionNaire questionNaire = questionService.findQuestionNaireByIdRequired(questionNaireId);

        Page<Answer> answerPage = answerRepository.findAllByQuestionNaire(pageable, questionNaire);
        Page<AnswerListResDto> answerListResDtos = answerPage.map(a -> a.fromListEntity());
        return answerListResDtos;
    }
}
