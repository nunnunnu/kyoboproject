package com.team5.kyobo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.kyobo.entity.AutherInfoEntity;
@Repository
public interface AutherInfoRepository extends JpaRepository<AutherInfoEntity, Long> {
    AutherInfoEntity findByAutherName(String name);
    
}
