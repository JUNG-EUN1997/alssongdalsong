package com.Alssongdalsong.category.dto;

import com.Alssongdalsong.category.domain.Category;
import com.Alssongdalsong.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySaveReqDto {
    private String name;
    public Category toEntity(Member member) {
        return Category.builder()
                .name(this.name)
                .createdMember(member)
                .build();
    }
}
