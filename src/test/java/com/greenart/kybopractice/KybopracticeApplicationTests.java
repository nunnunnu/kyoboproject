package com.greenart.kybopractice;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.kybopractice.entity.BookEntity;
import com.greenart.kybopractice.entity.MemberInfoEntity;
import com.greenart.kybopractice.entity.ReviewEntity;
import com.greenart.kybopractice.repository.BookRepositroy;
// import com.greenart.kybopractice.repository.MemberRepository;
import com.greenart.kybopractice.repository.ReviewRepository;

@SpringBootTest
class KybopracticeApplicationTests {
	@Autowired BookRepositroy bookRepo;
	@Autowired ReviewRepository reviewRepo;
	// @Autowired MemberRepository memberRepo;
	@Test
	void bookTest() {
		List<BookEntity> list = bookRepo.findAll();
		for(BookEntity r : list){
			System.out.println(r);
		}
		System.out.println(bookRepo.findBySeq(1L));
	}
	@Test
	void reviewTest(){
		List<ReviewEntity> list = reviewRepo.findAll();
		for(ReviewEntity r : list){
			System.out.println(r);
			
		}
		System.out.println("--------------------------------------");
		// List<ReviewEntity> bookreview = reviewRepo.findByBookSeq(1L);
		// for(ReviewEntity r : bookreview){
		// 	System.out.println(r);
		// }
	}
	@Test
	void reviewdetail(){
		// List<ReviewEntity> list = reviewRepo.findByBookSeq(2L);
		// for(ReviewEntity r : list){
		// 	System.out.println(r);
		// }
	}
	// @Test
	// @Transactional
	// void memberAdd(){
	// 	MemberInfoEntity member = new MemberInfoEntity();
	// 	member.setId("user999");
	// 	member.setPwd("1234");
	// 	memberRepo.save(member);
	// 	List<MemberInfoEntity> list = memberRepo.findAll();
	// 	for(MemberInfoEntity m : list){
	// 		System.out.println(m);
	// 	}
	// }
}
