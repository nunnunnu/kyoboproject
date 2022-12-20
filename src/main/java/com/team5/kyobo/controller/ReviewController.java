package com.team5.kyobo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.kyobo.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired ReviewService rService;

    @GetMapping()
    public ResponseEntity<Object> showReviewList(
        @RequestParam Long seq,
        @PageableDefault(size=10, sort="reviewDate",direction = Sort.Direction.DESC)  final Pageable page
    ){
        Map<String, Object> map =rService.showReview(seq, page);
        // System.out.println(map);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
}
