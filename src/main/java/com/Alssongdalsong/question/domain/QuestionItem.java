// 문제에 대한 문항은 보류


//package com.Alssongdalsong.question.domain;
//
//import com.Alssongdalsong.common.domain.BaseTimeEntity;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//// 테이블 순서
//// QuestionNaire > Question > QuestionItem
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class QuestionItem extends BaseTimeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//    @Builder.Default
//    private Boolean isAnswer = false;
//    @Builder.Default
//    private Integer itemOrder = 0; // 일반 order사용 시, workbench 버전과 충돌
//
//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question;
//
//}
