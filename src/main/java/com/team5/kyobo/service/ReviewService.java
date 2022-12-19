package com.team5.kyobo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team5.kyobo.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired ReviewRepository reviewRepo;
    public Map<String, Object> showReview(Long seq, Pageable page){  
        Map<String, Object> map = new LinkedHashMap<>();
        if(reviewRepo.count()==0){
            map.put("message", "등록된 리뷰가 없습니다.");
        }else{
            map.put("List", reviewRepo.findByBookSeq(seq,page));
        }
        return map;
    }
}
