package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.ReviewInfoEntity;
@Repository
public interface ReviewInfoRepository extends JpaRepository<ReviewInfoEntity, Long> {
    
}
