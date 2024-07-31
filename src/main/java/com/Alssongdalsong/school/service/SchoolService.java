package com.Alssongdalsong.school.service;

import com.Alssongdalsong.common.domain.CommonMsg;
import com.Alssongdalsong.school.domain.School;
import com.Alssongdalsong.school.dto.SchoolListResDto;
import com.Alssongdalsong.school.dto.SchoolSaveReqDto;
import com.Alssongdalsong.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School schoolCreate(SchoolSaveReqDto dto){
        School school = dto.toEntity();
        schoolRepository.save(school);
        return school;
    }

    public Page<SchoolListResDto> schoolList(Pageable pageable){
        Page<School> schoolPage = schoolRepository.findAll(pageable);
        Page<SchoolListResDto> schoolListResDtos = schoolPage.map(a -> a.fromListEntity());
        return schoolListResDtos;
    }



    public School schoolFindById(Long id){
        School school = schoolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CommonMsg.SCHOOL_NOT_FOUNT.getMessage()));
        return school;
    }
}
