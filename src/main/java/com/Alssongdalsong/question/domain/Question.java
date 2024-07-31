package com.Alssongdalsong.question.domain;

import com.Alssongdalsong.common.domain.BaseTimeEntity;
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
    private String answer; // 타입이 text면 문제에 정답 저장

    private String title;
    private String description;

    @Builder.Default
    private Integer itemOrder = 0; // 일반 order사용 시, workbench 버전과 충돌

    @Builder.Default
    private Boolean isRequired = false;
}
