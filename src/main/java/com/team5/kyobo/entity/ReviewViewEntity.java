package com.team5.kyobo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable //뷰랑 연결할때 입력을 막기위해 넣어주는 어노테이션
@Table(name = "review_info_view")
public class ReviewViewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ri_seq") private Long reviewSeq;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "ri_date") private Date reviewDate;
    @Column(name = "ri_content") private String reviewContent;
    @Column(name = "ri_score") private Integer reviewSroce;
    @Column(name = "ri_mi_seq") private Long memberSeq;
    @Column(name = "ri_bi_seq") private Long bookSeq;
    @Column(name = "mi_id") private String MemberId;
}
