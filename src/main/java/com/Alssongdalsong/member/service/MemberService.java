package com.Alssongdalsong.member.service;

import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.dto.MemberAdminSaveReqDto;
import com.Alssongdalsong.member.dto.MemberListResDto;
import com.Alssongdalsong.member.dto.MemberLoginDto;
import com.Alssongdalsong.member.dto.MemberSaveReqDto;
import com.Alssongdalsong.member.repository.MemberRepository;
import com.Alssongdalsong.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final SchoolService schoolService;

    @Autowired
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, SchoolService schoolService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.schoolService = schoolService;
    }

//    회원 생성
    public Member memberCreate(MemberSaveReqDto dto){
        Member member = dto.toEntity(passwordEncoder.encode(dto.getPassword()) ,schoolService.schoolFindById(dto.getSchoolId()));
        memberRepository.save(member);
        return member;
    }

    public Member memberAdminCreate(MemberAdminSaveReqDto dto){
        Member member = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        memberRepository.save(member);
        return member;
    }

//    회원 리스트
    public Page<MemberListResDto> memberList(Pageable pageable){
        Page<Member> members = memberRepository.findAll(pageable);
        Page<MemberListResDto> memberListResDtos = members.map(a -> a.fromListDto());
        return memberListResDtos;
    }

//    회원 그룹별 리스트

//    회원 상세

//    회원 수정

//    회원 삭제

//    회원 딥 삭제

//    로그인
    public Member login(MemberLoginDto dto){
    //        email 존재여부
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 이메일입니다."));

    //        password 일치여부
        if (!passwordEncoder.matches(dto.getPassword(),member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member;

    }


//    회원 이메일로 찾기
    public Member findByEmail(String email){
        Member member = memberRepository.findByEmail(email).orElse(null);
        return member;
    }

}
