package com.Alssongdalsong.question.domain;

import com.Alssongdalsong.category.domain.Category;
import com.Alssongdalsong.common.domain.BaseTimeEntity;
import com.Alssongdalsong.question.dto.QuestionListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 테이블 순서
// QuestionNaire > Question > QuestionItem

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class QuestionNaire extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String bgmPath; //배경음
    private String bgImagePath; // 배경이미지
    private String pointClickSoundPath; // 클릭 효과음

    public QuestionListResDto fromListEntity() {
        return QuestionListResDto.builder()
                .id(this.id)
                .title(this.title)
                .categoryName((this.category != null) ? this.category.getName() : null)
                .updateTime(this.getUpdateTime())
                .createdTime(this.getCreatedTime())
                .build();
    }
}
