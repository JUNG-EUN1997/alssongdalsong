package com.Alssongdalsong.answer.dto;

import com.Alssongdalsong.answer.domain.Answer;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.question.domain.Question;
import com.Alssongdalsong.question.domain.QuestionNaire;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerSaveQuestionReqDto {
    private Long questionNaireId;
    private Long questionId;
    @Builder.Default
    private Integer selectedValue = 0;
    @Builder.Default
    private String enterValue = null;
    private Boolean isCorrect;

    public Answer toEntity(Member member, QuestionNaire questionNaire , Question question) {
        return Answer.builder()
                .member(member)
                .questionNaire(questionNaire)
                .question(question)
                .selectedValue(this.selectedValue)
                .enterValue(this.enterValue)
                .isCorrect(this.isCorrect)
                .build();
    }
}
