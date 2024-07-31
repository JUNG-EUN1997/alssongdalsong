package com.Alssongdalsong.school.controller;

import com.Alssongdalsong.common.domain.CommonMsg;
import com.Alssongdalsong.common.dto.CommonResDto;
import com.Alssongdalsong.school.domain.School;
import com.Alssongdalsong.school.dto.SchoolListResDto;
import com.Alssongdalsong.school.dto.SchoolSaveReqDto;
import com.Alssongdalsong.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolController {
    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    //    학교생성
    @PostMapping("/school/create")
    public ResponseEntity<?> schoolCreate(@RequestBody SchoolSaveReqDto dto){
        School school = schoolService.schoolCreate(dto);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, CommonMsg.SCHOOL_CREATED, school.getId());
        return new ResponseEntity<>(commonResDto,HttpStatus.CREATED);
    }

    //    학교리스트
    @GetMapping("/school/list")
    public ResponseEntity<?> schoolList(Pageable pageable){
        Page<SchoolListResDto> schoolList = schoolService.schoolList(pageable);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, CommonMsg.LIST_RETURN, schoolList);
        return new ResponseEntity<>(commonResDto,HttpStatus.CREATED);
    }
}
