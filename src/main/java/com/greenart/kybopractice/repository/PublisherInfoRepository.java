package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.PublisherInfoEntity;
@Repository
public interface PublisherInfoRepository extends JpaRepository<PublisherInfoEntity, Long> {
    PublisherInfoEntity findByPublisherName(String name);
}
