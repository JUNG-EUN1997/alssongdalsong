package com.Alssongdalsong.common.service;


import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.domain.Role;
import com.Alssongdalsong.member.dto.MemberAdminSaveReqDto;
import com.Alssongdalsong.member.dto.MemberSaveReqDto;
import com.Alssongdalsong.member.repository.MemberRepository;
import com.Alssongdalsong.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class InitialDataLoader implements CommandLineRunner { // 프로그램 시작 되자마자 실행되는 comandline
    @Autowired
    private MemberService memberService;

    @Override
    public void run(String... args) throws Exception {
         if (memberService.findByEmail("admin@test.com") == null){
             memberService.memberAdminCreate(MemberAdminSaveReqDto.builder()
                     .email("admin@test.com")
                     .password("12341234")
                     .name("admin")
                     .phone("99900000000")
                     .role(Role.ADMIN)
                     .build());
         }

    }

}
