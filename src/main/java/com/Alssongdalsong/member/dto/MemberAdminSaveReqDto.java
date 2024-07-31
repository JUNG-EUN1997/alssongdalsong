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
public class MemberAdminSaveReqDto {
    private String email;
    private String password;
    private String name;
    private String phone;

    private Role role;

    public Member toEntity(String password) {
        return Member.builder()
                .email(this.email)
                .password(password)
                .name(this.name)
                .phone(this.phone)
                .role(this.role)

                .build();
    }
}
