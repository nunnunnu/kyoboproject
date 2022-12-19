package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.AutherInfoEntity;
@Repository
public interface AutherInfoRepository extends JpaRepository<AutherInfoEntity, Long> {
    AutherInfoEntity findByAutherName(String name);
    
}
