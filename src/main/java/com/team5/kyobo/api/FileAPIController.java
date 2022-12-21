package com.team5.kyobo.api;

import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.team5.kyobo.entity.CoverImageEntity;
import com.team5.kyobo.repository.BookIntroRepository;
import com.team5.kyobo.repository.CoverImageRepository;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
public class FileAPIController {

    @Value("${file.image.cover}") String cover_img_path; //springframework.beans임
    @Value("${file.image.introduce}") String intro_img_path;

    @Autowired CoverImageRepository cRepository;
    @Autowired BookIntroRepository iRepository;

    @GetMapping("/image/{type}/{file}")
    public ResponseEntity<Object> getImagedownload( //core.io.Resource import해야함
        @PathVariable String file, HttpServletRequest request,
        @PathVariable String type
    ) throws Exception
    {
        Map<String, Object> map = new LinkedHashMap<>();
        Path folderLocation = null;
        if (type.equals("cover")) {
            folderLocation = Paths.get(cover_img_path); //저장할 파일의 타입이 위치한 경로를 찾아옴
        } else if (type.equals("intro")) {
            folderLocation = Paths.get(intro_img_path); //저장할 파일의 타입이 위치한 경로를 찾아옴
        }else{
            map.put("status", false);
            map.put("message", "타입이 올바르지 않습니다. (예시 : cover, intro)");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        String ext = "jpg"; //프론트에서 작업하기 쉽게하기위해 임의로 확장자 고정시킴.
        //실제로는 이미지 주소에 확장자까지 적어서 값을 받아야하지만 uri만 입력해서 다운가능하게함 
        String exportName = file + "." + ext; //내보낼 파일 이름(확장자까지)
        Path targetFile = folderLocation.resolve(exportName); //파일경로 + 파일이름
        // 다운로드 가능한 형태로 변환하기 위한 Resource 객체 생성 
        Resource r = null;
        try {
            // 일반파일 -> Url로 첨부 가능한 형태로 변환 
            r = new UrlResource(targetFile.toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 첨부된 파일의 타입을 저장하기위한 변수 생성 
        String contentType = null;
        try {
            // 첨부할 파일의 타입 정보 산출 
            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            // 산출한 파일의 타입이 null 이라면 
            if (contentType == null) {
                // 일반 파일로 처리한다. 
                contentType = "application/octet-stream";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
            // 응답의 코드를 200 OK로 설정하고  산출한 타입을 응답에 맞는 형태로 변환 
            .contentType(MediaType.parseMediaType(contentType))
            // 내보낼 내용의 타입을 설정 (파일),  attachment; filename=\""+r.getFilename()+"\" 요청한 쪽에서 다운로드 한 파일의 이름을 결정 
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(contentType, "UTF-8") + "\"")
            .body(r);
            // 변환된 파일을 ResponseEntity에 추가 
    }
}