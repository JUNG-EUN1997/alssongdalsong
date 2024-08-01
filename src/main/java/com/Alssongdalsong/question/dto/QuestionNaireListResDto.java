package com.Alssongdalsong.question.dto;

import com.Alssongdalsong.question.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionNaireListResDto {
    private Long id;
    private String title;
    private String categoryName;

    private LocalDateTime updateTime;
    private LocalDateTime createdTime;
}
