package com.greenart.kybopractice.entity;

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
@Table(name = "book_intro_image")
public class BookIntroEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bii_seq") private Long introImageSeq;
    @Column(name = "bii_bi_seq") private Long bookSeq;
    @Column(name = "bii_image") private String introImage;
    
    
}
