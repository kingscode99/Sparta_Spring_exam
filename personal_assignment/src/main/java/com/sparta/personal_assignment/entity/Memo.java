package com.sparta.personal_assignment.entity;

import com.sparta.personal_assignment.dto.MemoRequestDto;
import javax.persistence.*;
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
    private String title;
    private String comment;

    @Column(nullable = false)
    private Long userId;

    public Memo(MemoRequestDto requestDto, Long userId) {
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
        this.userId = userId;
    }

    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
    }
}