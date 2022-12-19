package com.team5.kyobo.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookDetailViewRepository extends JpaRepository<BookDetailViewEntity, Long>{
    
}
