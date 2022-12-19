package com.team5.kyobo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.team5.kyobo.entity.AutherInfoEntity;
import com.team5.kyobo.entity.BookDetailViewEntity;
import com.team5.kyobo.entity.BookInfoEntity;
import com.team5.kyobo.entity.BookIntroEntity;
import com.team5.kyobo.entity.BookTextIntro;
import com.team5.kyobo.entity.BookTranslatorEntity;
import com.team5.kyobo.entity.BookWriterInfoEntity;
import com.team5.kyobo.entity.CoverImageEntity;
import com.team5.kyobo.entity.PublisherInfoEntity;
import com.team5.kyobo.repository.AutherInfoRepository;
import com.team5.kyobo.repository.BookDetailViewRepository;
import com.team5.kyobo.repository.BookInfoRepository;
import com.team5.kyobo.repository.BookIntroRepository;
import com.team5.kyobo.repository.BookTextIntroRepository;
import com.team5.kyobo.repository.BookTranslatorRepository;
import com.team5.kyobo.repository.BookWriterInfoRepository;
import com.team5.kyobo.repository.CoverImageRepository;
import com.team5.kyobo.repository.PublisherInfoRepository;
@Service
public class BookService {
    @Autowired BookDetailViewRepository bookRepo;
    @Autowired AutherInfoRepository aRepository;
    @Autowired BookTranslatorRepository tranRepository;
    @Autowired PublisherInfoRepository pRepository;
    @Autowired BookWriterInfoRepository wRepository; 
    @Autowired BookIntroRepository iRepository;
    @Autowired BookTextIntroRepository textRepository;
    @Autowired CoverImageRepository cRepository;
    @Autowired BookInfoRepository biRepo;

    public Map<String, Object> showDetailBookInfo(Long seq, Pageable page){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if(bookRepo.count()==0){
            map.put("status", false);
            map.put("message", "아직 등록된 책이 존재하지않습니다.");
            map.put("code", HttpStatus.NO_CONTENT); 
        }else{
            BookDetailViewEntity book = bookRepo.findBySeq(seq);
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

    public Map<String, Object> bookBestList(Pageable page){
        Map<String, Object> map = new LinkedHashMap<>();
        if(bookRepo.count()==0){
            map.put("status", false);
            map.put("message", "아직 등록된 책이 존재하지않습니다.");
            map.put("code", HttpStatus.NO_CONTENT); 
        }else{
            map.put("status", true);
            map.put("message", "베스트 리스트 조회를 성공했습니다.");
            map.put("list", bookRepo.findAllByOrderBySalesDesc(page));
            map.put("code", HttpStatus.OK); 
        }
        return map;
    }
}


    public void addBookInfo(
        String autherName,
        @Nullable String translatorName,
        String pubName,
        String title, Integer price, Double discount, String delivery, Date date, Integer sales,
        String introImage, String introText, String coverImage, String coverUri, String introUri
    ){
        String[] split = autherName.split(";");
        System.out.println(split.length);
        List<AutherInfoEntity> autherList = new ArrayList<AutherInfoEntity>();
        for(String name : split){
            AutherInfoEntity auther = new AutherInfoEntity();
            System.out.println(name);
            auther = aRepository.findByAutherName(name);
            if(auther==null){
                auther = new AutherInfoEntity();
                auther.setAutherName(name);
                auther = aRepository.save(auther);
            }
            autherList.add(auther);
        }
        PublisherInfoEntity pub = pRepository.findByPublisherName(pubName);
        if(pub==null){
            pub = new PublisherInfoEntity();
            pub.setPublisherName(pubName);
            pub = pRepository.save(pub);
        }
        BookInfoEntity book = null;
        if(translatorName!=null && !translatorName.equals("null")){
            BookTranslatorEntity tran = tranRepository.findByTranslatorName(translatorName);
            if(tran==null){
                tran = new BookTranslatorEntity();
                tran.setTranslatorName(translatorName);
                tran = tranRepository.save(tran);
                book = new BookInfoEntity(null, title, price, discount, delivery, date, pub.getPublisherSeq(), tran.getTranslatorSeq(), sales);
            }
        }else{
            book = new BookInfoEntity(null, title, price, discount, delivery, date, pub.getPublisherSeq(), null, sales);
        }
        book = biRepo.save(book);
        System.out.println(book);
        for(AutherInfoEntity a : autherList){
            BookWriterInfoEntity bwi = new BookWriterInfoEntity();
            bwi.setBookSeq(book.getBookSeq());
            bwi.setAutherSeq(a.getAutherSeq());
            bwi = wRepository.save(bwi);
        }
        
        BookIntroEntity image = new BookIntroEntity();
        image.setBookSeq(book.getBookSeq());
        image.setIntroImage(introImage);
        image.setIntroImageUri(introUri);
        image = iRepository.save(image);

        BookTextIntro text = new BookTextIntro();
        text.setBookSeq(book.getBookSeq());
        text.setIntroText(introText);
        text = textRepository.save(text);

        CoverImageEntity cover = new CoverImageEntity();
        cover.setBookSeq(book.getBookSeq());
        cover.setCoverImage(coverImage);
        cover.setCoverUri(coverUri);
        cover = cRepository.save(cover);

    }
}
