package com.Alssongdalsong.question.dto;

import com.Alssongdalsong.question.domain.Question;
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
    @Builder.Default
    private Integer itemOrder = 0;
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

    private String answerWord; // 주관식일 때 정답

    public Question toEntity(QuestionNaire questionNaire) {
        return Question.builder()
                .questionNaire(questionNaire)
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
                .answerWord(this.answerWord)
                .build();
    }
}

/*
[{
    "questionNaireId":1,
    "questionType":"OPTION",
    "title":"다음 중 올바른 단어를 사용한 문장은?",
    "description":"한국인이 자주 틀리는 맞춤법 제 100선 중 하나",
    "question1":"공기밥 한 그릇 추가요~",
    "isQuestion1":"false",
    "question2":"공깃밥 한 그릇 추가요~",
    "isQuestion2":"true"
}]


* */