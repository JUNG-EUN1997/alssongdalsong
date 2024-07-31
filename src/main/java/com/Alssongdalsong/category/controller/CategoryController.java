package com.Alssongdalsong.category.controller;

import com.Alssongdalsong.common.domain.CommonMsg;
import com.Alssongdalsong.common.dto.CommonResDto;
import com.Alssongdalsong.category.domain.Category;
import com.Alssongdalsong.category.dto.CategoryListResDto;
import com.Alssongdalsong.category.dto.CategorySaveReqDto;
import com.Alssongdalsong.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //    카테고리생성
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/category/create")
    public ResponseEntity<?> categoryCreate(@RequestBody CategorySaveReqDto dto){
        Category category = categoryService.categoryCreate(dto);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, CommonMsg.CATEGORY_CREATED, category.getId());
        return new ResponseEntity<>(commonResDto,HttpStatus.CREATED);
    }

    //    카테고리리스트
    @GetMapping("/category/list")
    public ResponseEntity<?> categoryList(Pageable pageable){
        Page<CategoryListResDto> categoryList = categoryService.categoryList(pageable);
        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, CommonMsg.LIST_RETURN, categoryList);
        return new ResponseEntity<>(commonResDto,HttpStatus.CREATED);
    }
}
