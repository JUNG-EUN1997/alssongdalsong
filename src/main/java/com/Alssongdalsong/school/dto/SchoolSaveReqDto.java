package com.Alssongdalsong.school.dto;

import com.Alssongdalsong.school.domain.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolSaveReqDto {
    private Integer schoolCode;
    private String name;
    private String address;

    public School toEntity() {
        return School.builder()
                .name(this.name)
                .address(this.address)
                .build();
    }
}
