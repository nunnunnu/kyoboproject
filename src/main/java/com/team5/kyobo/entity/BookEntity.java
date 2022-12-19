package com.team5.kyobo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable //뷰랑 연결할때 입력을 막기위해 넣어주는 어노테이션
@Table(name = "book_detail_view") 
public class BookEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bi_seq") private Long seq;
    @Column(name = "bi_title") private String title;
    @Column(name = "bi_price") private Integer price;
    @Column(name = "bi_discount") private String discount;
    @Column(name = "bi_delivery") private String delivery;
    @Column(name = "bi_reg_dt") private Date regDt;
    @Column(name = "bi_pi_seq") private Long publisherNo;
    @Column(name = "bi_ti_seq") private Long translatorNo;
    @Column(name = "bi_sales") private Integer sales;
    @Column(name = "bti_intro") private String introduce;
    @Column(name = "bii_image") private String datailImage;
    @Column(name = "bti_name") private String translatorName;
    @Column(name = "ai_name") private String autherName;
    @Column(name = "score") private Double reviewScore;
    @Column(name = "discount_price") private Integer dicountPrice;
    @Column(name = "point") private Integer Point;
    @Column(name = "review_count") private Integer reviewCnt;
    @Column(name = "ci_path") private String coverImage;
    @Column(name = "pi_name") private String publisherName;
}
