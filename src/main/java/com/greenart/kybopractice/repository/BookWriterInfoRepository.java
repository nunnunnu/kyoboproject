package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.BookWriterInfo;
@Repository
public interface BookWriterInfoRepository extends JpaRepository<BookWriterInfo, Long> {
    
}
