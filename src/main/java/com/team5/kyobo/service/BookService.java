package com.team5.kyobo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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

    public Map<String, Object> showDetailBookInfo(Long seq){
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
            map.put("list", bookRepo.findAll(page));
            map.put("code", HttpStatus.OK); 
        }
        return map;
    }

    @Value("${file.image.introduce}") String intro_img_path; //springframework.beans임
    @Value("${file.image.cover}") String cover_img_path;

    public void addBookInfo(
        String autherName,
        @Nullable String translatorName,
        String pubName,
        String title, Integer price, Double discount, String delivery, Date date, Integer sales,
        String introText, 
        @Nullable MultipartFile introFile,
        MultipartFile coverFile
    ){
        Path coverFolderLocation = Paths.get(cover_img_path); //커버 파일이 저장될 위치를 받아옴
        Calendar c = Calendar.getInstance(); 
        String saveIntroFileName = "kybointro"+"_"; 
        String iFileName="";
        if(introFile!=null){
            Path introFolderLocation = Paths.get(intro_img_path); //책 설명이미지의 주소를 받아옴
            String introOriginFileName = introFile.getOriginalFilename(); //설명 이미지의 파일이름을 가지고옴
            String[] iFile = introOriginFileName.split(("\\."));  //파일이름에서 확장자를 제외하기위해 .을 기준으로 문자열을 나눔
            String iExt = iFile[iFile.length-1]; //나눈 문자열의 마지막이 확장자를 뜻함
            for(int i=0;i<iFile.length-1;i++){
                iFileName += iFile[i]; //확장자를 제외한 파일의 이름
            }
            saveIntroFileName+=c.getTimeInMillis()+"."+iExt;  //저장될 파일 이름
            Path introTargerFile = introFolderLocation.resolve(introOriginFileName); //파일 경로 + 저장될 파일 이름 합치기
            //여기 saveIntroFileName이 들어가야하는데 잘못넣었음
            try{
                    Files.copy(introFile.getInputStream(), introTargerFile, StandardCopyOption.REPLACE_EXISTING); 
                }catch(Exception e){e.printStackTrace();}
            }

        String coverOriginFileName = coverFile.getOriginalFilename(); 
            String[] cFile = coverOriginFileName.split(("\\.")); 
            String cExt = cFile[cFile.length-1]; 
            String cFileName = "";
            for(int i=0;i<cFile.length-1;i++){
                cFileName += cFile[i]; 
            }
            String saveCoverFileName = "kybocover"+"_"; 
            saveCoverFileName+=c.getTimeInMillis()+"."+cExt; 

            Path coverTargerFile = coverFolderLocation.resolve(coverFile.getOriginalFilename()); //여기 saveCoverFileName이 들어가야하는데 잘못넣었음
            try{
                Files.copy(coverFile.getInputStream(), coverTargerFile, StandardCopyOption.REPLACE_EXISTING); 
            }catch(Exception e){e.printStackTrace();}

        String[] split = autherName.split(";");
        List<AutherInfoEntity> autherList = new ArrayList<AutherInfoEntity>();
        for(String name : split){
            AutherInfoEntity auther = new AutherInfoEntity();
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
        for(AutherInfoEntity a : autherList){
            BookWriterInfoEntity bwi = new BookWriterInfoEntity();
            bwi.setBookSeq(book.getBookSeq());
            bwi.setAutherSeq(a.getAutherSeq());
            bwi = wRepository.save(bwi);
        }
        if(introFile!=null){
            BookIntroEntity image = new BookIntroEntity();
            image.setBookSeq(book.getBookSeq());
            image.setIntroImage(saveIntroFileName);
            image.setIntroImageUri(iFileName);
            image = iRepository.save(image);
        }

        BookTextIntro text = new BookTextIntro();
        text.setBookSeq(book.getBookSeq());
        text.setIntroText(introText);
        text = textRepository.save(text);

        CoverImageEntity cover = new CoverImageEntity();
        cover.setBookSeq(book.getBookSeq());
        cover.setCoverImage(saveCoverFileName);
        cover.setCoverUri(cFileName);
        cover = cRepository.save(cover);
    }
}
