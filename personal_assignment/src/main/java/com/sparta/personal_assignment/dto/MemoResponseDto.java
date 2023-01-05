package com.sparta.personal_assignment.dto;

import com.sparta.personal_assignment.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {
    private final String title;
    private final String comment;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long id;
    private final String username;
    public MemoResponseDto(Memo memo, String username) {
        this.title = memo.getTitle();
        this.comment = memo.getComment();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.id = memo.getId();
        this.username = username;
    }

}
