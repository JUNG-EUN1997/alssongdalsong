package com.Alssongdalsong.school.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolListResDto {
    private Long id;

    private Integer schoolCode;
    private String name;
    private String address;
}
