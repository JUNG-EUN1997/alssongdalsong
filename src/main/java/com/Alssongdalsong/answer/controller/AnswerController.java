package com.Alssongdalsong.answer.controller;

import com.Alssongdalsong.answer.domain.Answer;
import com.Alssongdalsong.answer.dto.AnswerListResDto;
import com.Alssongdalsong.answer.dto.AnswerSaveQuestionReqDto;
import com.Alssongdalsong.answer.service.AnswerService;
import com.Alssongdalsong.common.domain.CommonMsg;
import com.Alssongdalsong.common.dto.CommonResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    //    Question 단위 답 생성
    @PostMapping("/answer/create/{questionId}")
    public ResponseEntity<?> answerCreate(@PathVariable(name = "questionId") Long questionId,
                                          @RequestBody AnswerSaveQuestionReqDto dto){
        Answer answer = answerService.answerCreatePerQuestion(dto, questionId);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, CommonMsg.LIST_RETURN, answer.getId());
        return new ResponseEntity<>(commonResDto,HttpStatus.CREATED);
    }

//    문제집별 답 리스트
    @GetMapping("/answer/list/{questionNaireId}")
    public ResponseEntity<?> answerList(@PathVariable(name = "questionNaireId") Long questionNaireId, Pageable pageable){
        Page<AnswerListResDto> answerList = answerService.answerList(pageable, questionNaireId);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, CommonMsg.LIST_RETURN, answerList);
        return new ResponseEntity<>(commonResDto,HttpStatus.OK);
    }


}
