package com.team5.kyobo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team5.kyobo.repository.ReviewViewRepository;

@Service
public class ReviewService {
    @Autowired ReviewViewRepository reviewRepo;
    public Map<String, Object> showReview(Long seq, Pageable page){  
        Map<String, Object> map = new LinkedHashMap<>();
        if(reviewRepo.countByBookSeq(seq)==0){
            map.put("message", "등록된 리뷰가 없습니다.");
            map.put("status", false);
            map.put("code", HttpStatus.NO_CONTENT);
        }else{
            map.put("message", "리뷰를 조회했습니다.");
            map.put("status", true);
            map.put("code", HttpStatus.OK);
            map.put("List", reviewRepo.findByBookSeq(seq,page));
        }
        System.out.println(map);
        return map;
    }
}
