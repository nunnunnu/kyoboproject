package com.team5.kyobo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review_info")
@DynamicInsert
@DynamicUpdate
public class ReviewInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ri_seq") private Long reviewSeq;
    @Column(name = "ri_date") private Date reviewDate;
    @Column(name = "ri_content") private String reviewContent;
    @Column(name = "ri_score") private Double reviewScore;
    @Column(name = "ri_mi_seq") private Long memberSeq;
    @Column(name = "ri_bi_seq") private Long bookSeq;
}
