package com.Alssongdalsong.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionListResDto {
    private Long id;
    private String title;
    private String categoryName;

    private LocalDateTime updateTime;
    private LocalDateTime createdTime;
}
