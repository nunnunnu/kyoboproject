package com.team5.kyobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.BookWriterInfoEntity;
@Repository
public interface BookWriterInfoRepository extends JpaRepository<BookWriterInfoEntity, Long>{
    
}
