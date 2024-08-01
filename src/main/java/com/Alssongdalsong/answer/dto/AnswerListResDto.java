package com.Alssongdalsong.answer.dto;

import com.Alssongdalsong.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerListResDto {
    private Long id;
    private String memberName;

    private String questionName;

    private Integer selectedValue;
    private String enterValue;
    private Boolean isCorrect;

    private LocalDateTime updateTime;
    private LocalDateTime createdTime;
}
