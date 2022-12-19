package com.team5.kyobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.BookTranslatorEntity;
@Repository
public interface BookTranslatorRepository extends JpaRepository<BookTranslatorEntity, Long> {
    BookTranslatorEntity findByTranslatorName(String name);
}
