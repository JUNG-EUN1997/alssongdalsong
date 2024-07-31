package com.Alssongdalsong.school.repository;

import com.Alssongdalsong.school.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long> {
    Optional<School> findBySchoolCode(Integer schoolCode);
}
