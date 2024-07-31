package com.Alssongdalsong.member.dto;

import com.Alssongdalsong.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberListResDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private Role role;

    private String schoolName;

    private LocalDateTime updateTime;
    private LocalDateTime createdTime;
}