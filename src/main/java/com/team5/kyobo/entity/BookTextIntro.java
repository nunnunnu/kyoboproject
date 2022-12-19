package com.team5.kyobo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_text_intro")
public class BookTextIntro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bti_seq") private Long introTextSeq;
    @Column(name = "bti_bi_seq") private Long bookSeq;
    @Column(name = "bti_intro") private String introText;
}