package com.Alssongdalsong.question.domain;

import com.Alssongdalsong.category.domain.Category;
import com.Alssongdalsong.common.domain.BaseTimeEntity;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.question.dto.QuestionNaireListResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String bgmPath; //배경음
    private String bgImagePath; // 배경이미지
    private String pointClickSoundPath; // 클릭 효과음

    @OneToMany(mappedBy = "questionNaire", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Question> questions = new ArrayList<>();

    public void updateImagePath(String s3BgImagePath, String s3BgmPath, String s3ClickSoundPath) {
        this.bgImagePath = s3BgImagePath;
        this.bgmPath = s3BgmPath;
        this.pointClickSoundPath = s3ClickSoundPath;
    }

    public QuestionNaireListResDto fromListEntity() {
        return QuestionNaireListResDto.builder()
                .id(this.id)
                .title(this.title)
                .categoryName((this.category != null) ? this.category.getName() : null)
                .updateTime(this.getUpdateTime())
                .createdTime(this.getCreatedTime())
                .build();
    }
}
