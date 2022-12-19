package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.BookInfoEntity;
@Repository
public interface BookInfoRepository extends JpaRepository<BookInfoEntity, Long> {
    
}
