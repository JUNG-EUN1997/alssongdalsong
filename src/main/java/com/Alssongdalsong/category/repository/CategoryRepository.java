package com.Alssongdalsong.category.repository;

import com.Alssongdalsong.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
