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
@Table(name = "cover_image")
public class CoverImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ci_seq") private Long coverImageSeq;
    @Column(name = "ci_path") private String coverImage;
    @Column(name = "ci_bi_seq") private Long bookSeq;
    @Column(name = "ci_uri") private String coverUri;
}
