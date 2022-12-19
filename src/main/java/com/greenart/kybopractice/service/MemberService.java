package com.greenart.kybopractice.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.greenart.kybopractice.entity.MemberInfoEntity;
import com.greenart.kybopractice.repository.MemberRepository;
import com.greenart.kybopractice.utils.AESAlgorithm;

@Controller
public class MemberService {
    @Autowired MemberRepository mRepository;
    public Map<String, Object> memberAdd(MemberInfoEntity member){
        Map<String, Object> map = new LinkedHashMap<>();
        if(mRepository.countById(member.getId())==0){
            try {
                member.setPwd(AESAlgorithm.Encrypt(member.getPwd()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            mRepository.save(member);
            map.put("status", true);
            map.put("message", "회원가입 성공");
            map.put("code", HttpStatus.OK);
        }else{
            map.put("status", false);
            map.put("message", member.getId()+"은/는 중복된 아이디입니다.");
            map.put("code", HttpStatus.BAD_REQUEST);
        }
        return map;
    }
    
}
