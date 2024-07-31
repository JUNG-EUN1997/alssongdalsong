package com.Alssongdalsong.member.controller;

import com.Alssongdalsong.common.domain.CommonMsg;
import com.Alssongdalsong.common.dto.CommonResDto;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.dto.MemberSaveReqDto;
import com.Alssongdalsong.member.service.MemberService;
import com.Alssongdalsong.member.dto.MemberListResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    회원 생성
    @PostMapping("/member/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto dto){
        Member member = memberService.memberCreate(dto);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, CommonMsg.MEMBER_CREATED, member.getId());
        return new ResponseEntity<>(commonResDto,HttpStatus.CREATED);
    }
//    회원 리스트
    @GetMapping("/member/list")
    public ResponseEntity<?> memberList(Pageable pageable){
        Page<MemberListResDto> memberList = memberService.memberList(pageable);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, CommonMsg.LIST_RETURN, memberList);
        return new ResponseEntity<>(commonResDto,HttpStatus.CREATED);
    }
//    회원 그룹별 리스트

//    회원 상세

//    회원 수정

//    회원 삭제

//    회원 딥 삭제

}
