package com.Alssongdalsong.question.domain;

import com.Alssongdalsong.common.domain.BaseTimeEntity;
import com.Alssongdalsong.question.dto.QuestionListResDto;
import com.Alssongdalsong.question.dto.QuestionNaireListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 테이블 순서
// QuestionNaire > Question > QuestionItem
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questionNaire_id")
    private QuestionNaire questionNaire;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private QuestionType questionType = QuestionType.OPTION;

    private String title;
    private String description;

    @Builder.Default
    private Integer itemOrder = 0; // 일반 order사용 시, workbench 버전과 충돌

    @Builder.Default
    private Boolean isRequired = false;


    private String question1; //문항1
    private Boolean isQuestion1; //문항1 정답 여부
    private String question2; //문항2
    private Boolean isQuestion2; //문항2 정답 여부
    private String question3; //문항3
    private Boolean isQuestion3; //문항3 정답 여부
    private String question4; //문항4
    private Boolean isQuestion4; //문항4 정답 여부

    //    private String answer; // 타입이 text면 문제에 정답 저장
    private String answerWord; // 타입이 text면 문제에 정답 저장


    public QuestionListResDto fromListEntity() {
        return QuestionListResDto.builder()
                .id(this.id)
                .questionType(this.questionType)
                .title(this.title)
                .description(this.description)
                .itemOrder(this.itemOrder)
                .isRequired(this.isRequired)
                .question1(this.question1)
                .isQuestion1(this.isQuestion1)
                .question2(this.question2)
                .isQuestion2(this.isQuestion2)
                .question3(this.question3)
                .isQuestion3(this.isQuestion3)
                .question4(this.question4)
                .isQuestion4(this.isQuestion4)
                .updateTime(this.getUpdateTime())
                .createdTime(this.getCreatedTime())
                .build();
    }
}
