package com.Alssongdalsong.member.dto;

import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.domain.Role;
import com.Alssongdalsong.school.domain.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSaveReqDto {
    private String email;
    private String password;
    private String name;
    private String phone;

    private Long schoolId;
    private Role role;

//    교사만 입력받는 정보
    @Builder.Default
    private Integer teacherCode = null;
    @Builder.Default
    private String position = null; // 직급
    @Builder.Default
    private String department = null;

//    학생만 입력받는 정보
    @Builder.Default
    private Integer grade = null; // 학년
    @Builder.Default
    private Integer section = null; // 반

    public Member toEntity(String password, School school) {
        return Member.builder()
                .email(this.email)
                .password(password)
                .name(this.name)
                .phone(this.phone)
                .school(school)
                .role(this.role)

//                교사용 정보
                .teacherCode(this.teacherCode)
                .position(this.position)
                .department(this.department)

//                학생용 정보
                .grade(this.grade)
                .section(this.section)
                .build();
    }
}
