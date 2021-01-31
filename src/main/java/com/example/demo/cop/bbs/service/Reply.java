package com.example.demo.cop.bbs.service;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "replies")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rep_num") private int repNum;
    @Column(name = "content") private String content;

    @ManyToOne
    @JoinColumn(name = "art_num")
    private Article article;
}