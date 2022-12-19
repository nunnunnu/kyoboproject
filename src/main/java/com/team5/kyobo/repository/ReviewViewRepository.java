package com.team5.kyobo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.ReviewViewEntity;
@Repository
public interface ReviewViewRepository extends JpaRepository<ReviewViewEntity, Long> {
    Page<ReviewViewEntity> findByBookSeq(Long bookSeq, Pageable page);
    Integer countByBookSeq(Long bookSeq);
}
