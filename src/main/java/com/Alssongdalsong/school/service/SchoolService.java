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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public School schoolCreate(SchoolSaveReqDto dto){
        Optional<School> school = schoolRepository.findBySchoolCode(dto.getSchoolCode());
        if(school.isEmpty()){
            School saveSchool = dto.toEntity();
            schoolRepository.save(saveSchool);
            return saveSchool;
        }else{
            throw new IllegalArgumentException("중복된 학교 코드입니다.");
        }
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
