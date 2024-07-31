package com.Alssongdalsong.category.domain;

import com.Alssongdalsong.category.dto.CategoryListResDto;
import com.Alssongdalsong.common.domain.BaseTimeEntity;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.school.domain.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Quiz에 대한 카테고리

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    카테고리를 생성한 사람의 id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member createdMember;

    public CategoryListResDto fromListEntity() {
        return CategoryListResDto.builder()
                .id(this.id)
                .name(this.name)
                .memberName((this.createdMember!=null) ? this.createdMember.getName() : null)
                .updateTime(this.getUpdateTime())
                .createdTime(this.getCreatedTime())
                .build();
    }
}
