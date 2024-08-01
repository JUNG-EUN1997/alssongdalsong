package com.Alssongdalsong.question.service;

import com.Alssongdalsong.category.domain.Category;
import com.Alssongdalsong.category.service.CategoryService;
import com.Alssongdalsong.common.service.UploadAwsFileService;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.service.MemberService;
import com.Alssongdalsong.question.domain.Question;
import com.Alssongdalsong.question.domain.QuestionNaire;
import com.Alssongdalsong.question.dto.QuestionDetResDto;
import com.Alssongdalsong.question.dto.QuestionSaveReqDto;
import com.Alssongdalsong.question.repository.QuestionNaireRepository;
import com.Alssongdalsong.question.dto.QuestionListResDto;
import com.Alssongdalsong.question.dto.QuestionContainerSaveReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class QuestionService {
    private final QuestionNaireRepository questionNaireRepository;
    private final CategoryService categoryService;
    private final UploadAwsFileService uploadAwsFileService;
    private final MemberService memberService;

    @Autowired
    public QuestionService(QuestionNaireRepository questionNaireRepository, CategoryService categoryService, UploadAwsFileService uploadAwsFileService, MemberService memberService) {
        this.questionNaireRepository = questionNaireRepository;
        this.categoryService = categoryService;
        this.uploadAwsFileService = uploadAwsFileService;
        this.memberService = memberService;
    }

    @Transactional
    public QuestionNaire questionCreate(QuestionContainerSaveReqDto dto,
                                        MultipartFile bgmPathDto, MultipartFile bgImagePathDto, MultipartFile pointClickSoundPathDto){
        String memberEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.findByEmail(memberEmail);

        Category category = categoryService.findByIdRequired(dto.getCategoryId());
        QuestionNaire questionNaire = dto.toEntity(member, category, "","","");

        for(QuestionSaveReqDto questionDto : dto.getQuestionSaveReqDtos()){
            Question question = questionDto.toEntity(questionNaire);
            questionNaire.getQuestions().add(question);
        }

        try{
            String s3BgImagePath = null;
            String s3BgmPath = null;
            String s3ClickSoundPath = null;

            MultipartFile bgImagePath = bgImagePathDto;
            if(!bgImagePath.isEmpty()){
                String bgImagePathFileName = questionNaire.getId() + "_BGIMG_"  + bgImagePath.getOriginalFilename();
                byte[] bgImagePathByte =  bgImagePath.getBytes();
                s3BgImagePath = uploadAwsFileService.UploadAwsFileAndReturnPath(bgImagePathFileName,bgImagePathByte);
            }

            MultipartFile bgmPath = bgmPathDto;
            if(!bgmPath.isEmpty()){
                String bgmPathPathFileName = questionNaire.getId() + "_BGM_" + bgmPath.getOriginalFilename();
                byte[] bgmPathPathByte =  bgImagePath.getBytes();
                s3BgmPath = uploadAwsFileService.UploadAwsFileAndReturnPath(bgmPathPathFileName,bgmPathPathByte);
            }

            MultipartFile clickSoundPath = pointClickSoundPathDto;
            if(!clickSoundPath.isEmpty()){
                String clickSoundPathPathFileName = questionNaire.getId() + "_SOUNDCLK_" + clickSoundPath.getOriginalFilename();
                byte[] clickSoundPathPathByte =  bgImagePath.getBytes();
                s3ClickSoundPath = uploadAwsFileService.UploadAwsFileAndReturnPath(clickSoundPathPathFileName,clickSoundPathPathByte);
            }

            questionNaire.updateImagePath(s3BgImagePath,s3BgmPath,s3ClickSoundPath);

        }catch (IOException e) {
            throw new RuntimeException("파일 저장 실패");
        }

        QuestionNaire savedQuestionNaire = questionNaireRepository.save(questionNaire);
        return savedQuestionNaire;
    }

    public Page<QuestionListResDto> questionList(Pageable pageable){
        String memberEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.findByEmail(memberEmail);
        Page<QuestionNaire> questionPage = questionNaireRepository.findAllByMember(pageable, member);
        Page<QuestionListResDto> questionListResDtos = questionPage.map(a -> a.fromListEntity());
        return questionListResDtos;
    }

    public Page<QuestionListResDto> questionAllList(Pageable pageable){
        Page<QuestionNaire> questionPage = questionNaireRepository.findAll(pageable);
        Page<QuestionListResDto> questionListResDtos = questionPage.map(a -> a.fromListEntity());
        return questionListResDtos;
    }

    public QuestionDetResDto questionDetail(Long id){
        return null;
    }
}
