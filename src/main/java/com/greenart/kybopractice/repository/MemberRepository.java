package com.greenart.kybopractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenart.kybopractice.entity.MemberInfoEntity;
@Repository
public interface MemberRepository extends JpaRepository<MemberInfoEntity, Long> {
    Integer countById(String id);
}
