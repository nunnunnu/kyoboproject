package com.team5.kyobo.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.kyobo.service.BookService;
import com.team5.kyobo.service.ReviewService;

@RestController
@RequestMapping("/api/book")
public class bookController {
    @Autowired BookService bService;
    @Autowired ReviewService rService;

    @GetMapping("/detail")
    public ResponseEntity<Object> detailBookInfo(@RequestParam Long seq,
        @PageableDefault(size=5, sort="reviewDate",direction = Sort.Direction.DESC)  final Pageable page
    ){
        Map<String, Object> map = bService.showDetailBookInfo(seq, page);
        if((boolean) map.get("status")){
            map.put("review", rService.showReview(seq, page));
        }
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
    @PutMapping("/add")
    public Map<String, Object> addBookInfo(
        @RequestParam String autherName
        ,@RequestParam @Nullable String translatorName
        ,@RequestParam String pubName
        ,@RequestParam String title
        ,@RequestParam Integer price
        ,@RequestParam Double discount
        ,@RequestParam String delivery
        ,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
        ,@RequestParam Integer sales
        ,@RequestParam String introImage
        ,@RequestParam String introText
        ,@RequestParam String coverImage
        ,@RequestParam String introUri
        ,@RequestParam String coverUri
    ){
        Map<String, Object> map = new LinkedHashMap<>();
        bService.addBookInfo(autherName, translatorName, pubName, title, price, 
            discount, delivery, date, sales, introImage, introText, coverImage, coverUri, introUri);
        map.put("status", true);
        map.put("message", "책 정보가 등록되었습니다.");
        return map;
    }
}