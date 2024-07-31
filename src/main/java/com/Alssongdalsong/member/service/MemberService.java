package com.Alssongdalsong.member.service;

import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.dto.MemberListResDto;
import com.Alssongdalsong.member.dto.MemberSaveReqDto;
import com.Alssongdalsong.member.repository.MemberRepository;
import com.Alssongdalsong.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final SchoolService schoolService;

    @Autowired
    public MemberService(MemberRepository memberRepository, SchoolService schoolService) {
        this.memberRepository = memberRepository;
        this.schoolService = schoolService;
    }

//    회원 생성
    public Member memberCreate(MemberSaveReqDto dto){
        Member member = dto.toEntity(schoolService.schoolFindById(dto.getSchoolId()));
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


}
