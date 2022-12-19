package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.BookTranslatorEntity;
@Repository
public interface BookTranslatorRepository extends JpaRepository<BookTranslatorEntity, Long> {
    BookTranslatorEntity findByTranslatorName(String name);
}
