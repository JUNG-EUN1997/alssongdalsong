package com.Alssongdalsong.common.domain;


public enum CommonMsg {
    LIST_RETURN("리스트를 성공적으로 불러왔습니다."),
    DETAIL_RETURN("상세 정보를 성공적으로 불러왔습니다."),
//    MEMBER 영역
    MEMBER_CREATED("회원이 성공적으로 생성되었습니다."),

//    SCHOOL 영역
    SCHOOL_CREATED("학교가 성공적으로 생성되었습니다."),
    SCHOOL_NOT_FOUNT("존재하지 않는 학교 입니다."),

//    CATEGORY 영역
    CATEGORY_CREATED("카테고리가 성공적으로 생성되었습니다."),

//    QUESTION 영역
    QUESTION_CREATED("문제가 성공적으로 생성되었습니다")

    ;

    private final String message;

    CommonMsg(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
