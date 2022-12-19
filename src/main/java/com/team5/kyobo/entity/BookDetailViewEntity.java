package com.team5.kyobo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable 
@Table(name = "book_detail_view") 
public class BookDetailViewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bi_seq") private Long seq;
    @Column(name = "bi_price") private Integer price ;
    @Column(name = "bi_title") private String title ;
    @Column(name = "bi_discount") private String discount;
    @Column(name = "bi_delivery") private String delivery;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "bi_reg_dt") private Date regDt ;
    @Column(name = "bi_pi_seq") private Long publisherNo ;
    @Column(name = "bi_ti_seq") private Long translatorNo ;
    @Column(name = "bi_sales") private Integer sales ;
    @Column(name = "bti_intro") private String textIntroduce ;
    @Column(name = "bii_image") private String imageIntroduce ;
    @Column(name = "bti_name") private String translatorName;
    @Column(name = "ai_name") private String autherName;
    @Column(name = "score") private Double score;
    @Column(name = "discount_price") private Integer discountPrice ;
    @Column(name = "point") private Integer point;
    @Column(name = "review_count") private Integer reivewCount ;
    @Column(name = "ci_path") private String coverImage;
    @Column(name = "pi_name") private String publisherName;
    @Column(name = "ranking") private Integer rank ;
    @Column(name = "bii_uri") private String introImageUri ;
    @Column(name = "ci_uri") private String coverImageUri ;

}
