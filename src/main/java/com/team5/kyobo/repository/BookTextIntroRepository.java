package com.team5.kyobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.BookTextIntro;
@Repository
public interface BookTextIntroRepository extends JpaRepository<BookTextIntro, Long> {
    
}
