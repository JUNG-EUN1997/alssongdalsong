package com.Alssongdalsong.member.controller;

import com.Alssongdalsong.common.auth.JwtTokenProvider;
import com.Alssongdalsong.common.domain.CommonMsg;
import com.Alssongdalsong.common.dto.CommonResDto;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.dto.MemberLoginDto;
import com.Alssongdalsong.member.dto.MemberSaveReqDto;
import com.Alssongdalsong.member.service.MemberService;
import com.Alssongdalsong.member.dto.MemberListResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    @Qualifier("11")
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider, @Qualifier("11")RedisTemplate<String, Object> redisTemplate) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
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

//    로그인
    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody MemberLoginDto dto){
    //        email, password가 일치한지 검증
        Member member = memberService.login(dto);

    //        일치할경우 accessToken 생성 (accessToken = JWT Token)
        String jwtToken = jwtTokenProvider.createToken(member.getEmail(), member.getRole().toString());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getEmail(), member.getRole().toString());

    //        redis에 email과 rt를 key:value로 하여 저장
        redisTemplate.opsForValue().set(member.getEmail(),refreshToken, 240, TimeUnit.HOURS); // 240시간
        Map<String,Object> loginInfo = new HashMap<>();
        loginInfo.put("id",member.getId());
        loginInfo.put("token",jwtToken);
        loginInfo.put("refresh",refreshToken);

        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK,"login is successful",loginInfo);
        return new ResponseEntity<>(commonResDto, HttpStatus.OK);
    }

}
