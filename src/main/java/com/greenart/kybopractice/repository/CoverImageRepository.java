package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.CoverImageEntity;
@Repository
public interface CoverImageRepository extends JpaRepository<CoverImageEntity, Long> {
    
}
