package com.greenart.kybopractice.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenart.kybopractice.entity.MemberInfoEntity;
import com.greenart.kybopractice.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired MemberService mService;

    @PutMapping("/add")
    public ResponseEntity<Object> memberAdd(@RequestBody MemberInfoEntity data){
        Map<String, Object> map = mService.memberAdd(data);
        return new ResponseEntity<>(map, (HttpStatus)map.get("code"));
    }
}
