package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.BookTextIntro;
@Repository
public interface BookTextIntroRepository extends JpaRepository<BookTextIntro, Long> {
    
}
