package com.sparta.personal_assignment.entity;

import com.sparta.personal_assignment.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nickname;
    private String title;
    private String comment;
    private String pw;


    public Memo(MemoRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
        this.pw = requestDto.getPw();
    }

    public void update(MemoRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
    }
}