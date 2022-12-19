package com.team5.kyobo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.team5.kyobo.entity.BookDetailViewEntity;
import com.team5.kyobo.repository.BookDetailViewRepository;

@SpringBootTest
class KyoboApplicationTests {
	@Autowired BookDetailViewRepository bViewRepository;
	@Test
	void 책리스트조회() {
		List<BookDetailViewEntity> list = bViewRepository.findAll();
		for(BookDetailViewEntity b : list){
			System.out.println(b);
		}
	}

}
