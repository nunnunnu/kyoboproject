package com.team5.kyobo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.BookEntity;
@Repository
public interface BookRepositroy extends JpaRepository<BookEntity, Long>{
    BookEntity findBySeq(Long seq);
    Page<BookEntity> findAllByOrderBySalesDesc(Pageable page);
}
