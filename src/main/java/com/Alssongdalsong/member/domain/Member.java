package com.Alssongdalsong.member.domain;

import com.Alssongdalsong.common.domain.BaseTimeEntity;
import com.Alssongdalsong.member.dto.MemberListResDto;
import com.Alssongdalsong.school.domain.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;

    @Column(nullable = false)
    private String name;
    private String phone;

//    교사만 입력받는 정보
    @Column(unique = true, nullable = true)
    private Integer teacherCode;
    private String position; // 직급
    private String department; // 부서
//    교사만 입력받는 정보 종료


//    학생만 입력받는 정보 시작
    private Integer grade; // 학년
    private Integer section; // 반
//    학생만 입력받는 정보 종료


    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.STUDENT;


    public MemberListResDto fromListDto() {
        return MemberListResDto.builder()
                .id(this.id)
                .email(this.email)
                .name(this.name)
                .phone(this.phone)
                .role(this.role)
                .schoolName(this.school.getName())
                .updateTime(this.getUpdateTime())
                .createdTime(this.getCreatedTime())
                .build();
    }
}
