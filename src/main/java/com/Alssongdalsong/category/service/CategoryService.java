package com.Alssongdalsong.category.service;

import com.Alssongdalsong.category.domain.Category;
import com.Alssongdalsong.category.dto.CategoryListResDto;
import com.Alssongdalsong.category.dto.CategorySaveReqDto;
import com.Alssongdalsong.category.repository.CategoryRepository;
import com.Alssongdalsong.member.domain.Member;
import com.Alssongdalsong.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MemberService memberService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, MemberService memberService) {
        this.categoryRepository = categoryRepository;
        this.memberService = memberService;
    }

    @Transactional
    public Category categoryCreate(CategorySaveReqDto dto){
        String memberEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.findByEmail(memberEmail);

        Category saveCategory = dto.toEntity(member);
        categoryRepository.save(saveCategory);
        return saveCategory;
    }

    public Page<CategoryListResDto> categoryList(Pageable pageable){
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        Page<CategoryListResDto> categoryListResDtos = categoryPage.map(a -> a.fromListEntity());
        return categoryListResDtos;
    }
}
