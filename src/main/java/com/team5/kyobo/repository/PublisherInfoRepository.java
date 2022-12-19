package com.team5.kyobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.PublisherInfoEntity;

@Repository
public interface PublisherInfoRepository extends JpaRepository<PublisherInfoEntity, Long>{
    PublisherInfoEntity findByPublisherName(String name);
}
