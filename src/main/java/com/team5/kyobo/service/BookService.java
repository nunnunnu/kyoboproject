package com.team5.kyobo.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team5.kyobo.entity.BookEntity;
import com.team5.kyobo.repository.AutherInfoRepository;
import com.team5.kyobo.repository.BookInfoRepository;
import com.team5.kyobo.repository.BookIntroRepository;
import com.team5.kyobo.repository.BookRepositroy;
import com.team5.kyobo.repository.BookTextIntroRepository;
import com.team5.kyobo.repository.BookTranslatorRepository;
import com.team5.kyobo.repository.BookWriterInfoRepository;
import com.team5.kyobo.repository.CoverImageRepository;
import com.team5.kyobo.repository.PublisherInfoRepository;
@Service
public class BookService {
    @Autowired BookRepositroy bookRepo;
    @Autowired AutherInfoRepository aRepository;
    @Autowired BookTranslatorRepository tranRepository;
    @Autowired PublisherInfoRepository pRepository;
    @Autowired BookWriterInfoRepository wRepository; 
    @Autowired BookIntroRepository iRepository;
    @Autowired BookTextIntroRepository textRepository;
    @Autowired CoverImageRepository cRepository;
    @Autowired BookInfoRepository biRepo;
    @Autowired EntityManager em;

    public Map<String, Object> showDetailBookInfo(Long seq, Pageable page){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if(bookRepo.count()==0){
            map.put("status", false);
            map.put("message", "아직 등록된 책이 존재하지않습니다.");
            map.put("code", HttpStatus.NO_CONTENT); 
        }else{
            BookEntity book = bookRepo.findBySeq(seq);
            if(book==null){
                map.put("status", false);
                map.put("message", "일치하는 책이 존재하지 않습니다. 책번호를 다시 확인해주세요");
                map.put("code", HttpStatus.BAD_REQUEST);
            }else{
                map.put("status", true);
                map.put("message", "책을 조회했습니다.");
                map.put("code", HttpStatus.OK);
                map.put("bookInfo", book);
            }
        }
        return map;
    }
}
