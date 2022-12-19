package com.team5.kyobo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{
    Page<ReviewEntity> findByBookSeq(Long bookSeq, Pageable page);
    Integer countByBookSeq(Long bookSeq);

}
