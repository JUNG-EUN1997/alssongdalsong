package com.Alssongdalsong.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryListResDto {
    private Long id;
    private String name;
    private String memberName;

    private LocalDateTime updateTime;
    private LocalDateTime createdTime;
}
