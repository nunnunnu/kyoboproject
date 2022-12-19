package com.greenart.kybopractice.entity;

import java.util.Date;

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
@Table(name = "book_writer_info")
public class BookWriterInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bwi_seq") private Long seq;
    @Column(name = "bwi_bi_seq") private Long bookSeq;
    @Column(name = "bwi_ai_seq") private Long autherSeq;
    
}
