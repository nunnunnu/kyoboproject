package com.team5.kyobo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class bookController {
  @Autowired BookService bService;
  @GetMapping("/list")

    public ResponseEntity<Object> detailBookInfo(
        @PageableDefault(size=10, sort="sales",direction = Sort.Direction.DESC)  final Pageable page
    ){
        Map<String, Object> map = bService.bookBestList(page);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
  
}
