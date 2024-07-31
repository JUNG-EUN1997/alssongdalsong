package com.Alssongdalsong.question.service;

import com.Alssongdalsong.category.domain.Category;
import com.Alssongdalsong.category.service.CategoryService;
import com.Alssongdalsong.question.domain.QuestionNaire;
import com.Alssongdalsong.question.dto.QuestionDetResDto;
import com.Alssongdalsong.question.repository.QuestionNaireRepository;
import com.Alssongdalsong.question.dto.QuestionListResDto;
import com.Alssongdalsong.question.dto.QuestionContainerSaveReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService {
    private final QuestionNaireRepository questionNaireRepository;
    private final CategoryService categoryService;

    @Autowired
    public QuestionService(QuestionNaireRepository questionNaireRepository, CategoryService categoryService) {
        this.questionNaireRepository = questionNaireRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public QuestionNaire questionCreate(QuestionContainerSaveReqDto dto){
        Category category = categoryService.findByIdRequired(dto.getCategoryId());
        QuestionNaire saveQuestion = dto.toEntity(category, "","","");
        questionNaireRepository.save(saveQuestion);
        return saveQuestion;
    }

    public Page<QuestionListResDto> questionList(Pageable pageable){
        Page<QuestionNaire> questionPage = questionNaireRepository.findAll(pageable);
        Page<QuestionListResDto> questionListResDtos = questionPage.map(a -> a.fromListEntity());
        return questionListResDtos;
    }

    public QuestionDetResDto questionDetail(){
        return null;
    }
}
