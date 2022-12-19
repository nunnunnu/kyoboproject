package com.team5.kyobo.api;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@RestController
public class FileAPIController {
    // 파일 업로드, 다운로드는 어느 프로젝프든 거의 동일함. 복붙추천
    @Value("${file.image.todo}") String todo_img_path; //springframework.beans임
    @Value("${file.image.member}") String member_img_path; 
    //이것도 DI임. 이미지 파일의 경로가 바뀌어도 application.properties만 고쳐주면 됨

    @PutMapping("/{type}/upload") //todo이미지를 올릴것인지 file이미지를 올릴것인지
    public ResponseEntity<Object> putImageUpload(
        @PathVariable String type,
        @RequestPart MultipartFile file //파일을 받는 객체. postman에 file이라고 변수 그대로 적어주어야함
        ){
            Map<String, Object> map = new LinkedHashMap<>();
            System.out.println(file.getOriginalFilename()); //업로드 할 파일의 원본이름 확장자까지 출력
            //Path - 폴더 및 파일의 위치를 나타내는 객체, Paths - 폴더 및 파일을 가져오고 경로를 만들기 위한 파일 유틸리티 클래스
            Path folderLocation = null; //todo_img_path 문자열로부터 실제 폴더 경로를 가져옴.
            if(type.equals("todo")){
                folderLocation = Paths.get(todo_img_path);
                
            }else if(type.equals("member")){
                folderLocation = Paths.get(member_img_path);
            }else{
                map.put("status", false);
                map.put("message", "타입정보가 잘못되었습니다. ex:/todo/upload, /member/upload");
                return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
            }
            Path targerFile = folderLocation.resolve(file.getOriginalFilename()); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로 생성
            try{
                //Files는 파일 처리에 대한 유틸리티 클래스
                //copy - 복사, file.getInputStream() - 파일을 열어서 파일의 내용을 읽는 준비
                //targetFile 경로로, standardCopyOption.REPLACE_EXISTING - 같은 파일이 있다면 덮어쓰기.
                Files.copy(file.getInputStream(), targerFile, StandardCopyOption.REPLACE_EXISTING); 
            }catch(Exception e){e.printStackTrace();}
            return new ResponseEntity<>(map, HttpStatus.OK);
        } //파일 업로드 메소드

        @GetMapping("/image/{filename}")
        public ResponseEntity<Resource> putImageUpload( //core.io.Resource import해야함
        @PathVariable String filename, HttpServletRequest request
        ){
            //Path - 폴더 및 파일의 위치를 나타내는 객체, Paths - 폴더 및 파일을 가져오고 경로를 만들기 위한 파일 유틸리티 클래스
            Path folderLocation = Paths.get(todo_img_path); //todo_img_path 문자열로부터 실제 폴더 경로를 가져옴.
            Path targerFile = folderLocation.resolve(filename); //폴더 경로와 파일의 이름을 합쳐서 목표 파일의 경로 생성
            //다운로드 가능한 형태로 변환하기 위해 Resource객체 생성함
            Resource r = null; 
            try{
                //일반파일 -> Url로 첨부 가능한 형태로 변환
                r = new UrlResource(targerFile.toUri());
            }catch(Exception e){e.printStackTrace();} //fileNotFoundException이 많이 나옴
            //첨부된 파일의 타입을 저장하기 위한 변수 생성
            String contentType = null;
            try{
                //첨부할 파일의 타입정보 산출
                contentType =  request.getServletContext().getMimeType(r.getFile().getAbsolutePath()); 
                if(contentType == null){ //산출한 파일의 타입이 null이면 
                    //일반 파일로 처리
                    contentType = "application/octet-stream"; //파일종류에따라서 동작이 다름. 만약 contentType이 안들어오면 그냥 다운로드함.
                }
            }catch(Exception e){e.printStackTrace();}
            return ResponseEntity.ok() // 200 OK가 나옴
                .contentType(MediaType.parseMediaType(contentType)) //산출한 타입을 응답에 맞는 형태로 변환
                //내보낼 내용의 타입을 설정(파일), 
                // attachment; filename*=\""+r.getFilename()+"\" - 요청한 쪽에서 다운로드한 파일의 이름을 결정(원본 파일 이름 그대로 결정)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+r.getFilename()+"\"")
                .body(r); //변환된 파일을 ResponseEntity에 추가
        }
}