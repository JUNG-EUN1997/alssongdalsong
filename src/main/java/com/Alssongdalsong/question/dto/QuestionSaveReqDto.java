package com.Alssongdalsong.question.dto;

import com.Alssongdalsong.question.domain.QuestionNaire;
import com.Alssongdalsong.question.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionSaveReqDto {
    private Long questionNaireId;
    private QuestionType questionType;
    private String title;
    private String description;
    private Integer itemOrder;
    private Boolean isRequired;

    private String question1; //문항1
    private Boolean isQuestion1; //문항1 정답 여부
    private String question2; //문항2
    private Boolean isQuestion2; //문항2 정답 여부
    private String question3; //문항3
    private Boolean isQuestion3; //문항3 정답 여부
    private String question4; //문항4
    private Boolean isQuestion4; //문항4 정답 여부

    private String answerWord; // 주관식일 때 정답
}
