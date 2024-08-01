package com.Alssongdalsong.question.dto;

import org.springframework.web.multipart.MultipartFile;

public class QuestionNaireFileSaveReqDto {
    private MultipartFile bgmPath; //배경음
    private MultipartFile bgImagePath; // 배경이미지
    private MultipartFile pointClickSoundPath; // 클릭 효과음
}
