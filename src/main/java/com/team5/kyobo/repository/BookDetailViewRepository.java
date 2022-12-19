package com.team5.kyobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.BookDetailViewEntity;
@Repository
public interface BookDetailViewRepository extends JpaRepository<BookDetailViewEntity, Long>{
    
}
