package com.Alssongdalsong.answer.domain;

import com.Alssongdalsong.answer.dto.AnswerListResDto;
import com.Alssongdalsong.common.domain.BaseTimeEntity;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.question.domain.Question;
import com.Alssongdalsong.question.domain.QuestionNaire;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "questionNaire_id")
    private QuestionNaire questionNaire;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;



    private Integer selectedValue; // 객관식일 때 선택 값
    private String enterValue; // 주관식일 때 입력 값

    private Boolean isCorrect; // 맞음 여부 : 추후 문제의 답변이 업데이트 될 것을 대비

    public AnswerListResDto fromListEntity() {
        return AnswerListResDto.builder()
                .id(this.id)
                .memberName(this.member.getName())
                .questionName(this.question.getTitle())
                .selectedValue(this.selectedValue)
                .enterValue(this.enterValue)
                .isCorrect(this.isCorrect)
                .updateTime(this.getUpdateTime())
                .createdTime(this.getCreatedTime())
                .build();
    }
}
