package com.sparta.personal_assignment.dto;

import com.sparta.personal_assignment.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class IgnorePwDto {
    private final String nickname;
    private final String title;
    private final String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;

    public IgnorePwDto(Memo memo) {
        this.nickname = memo.getNickname();
        this.title = memo.getTitle();
        this.comment = memo.getComment();
        this.createdAt = memo.getCreatedAt();
        this.modifiedAt = memo.getModifiedAt();
        this.id = memo.getId();
    }
}
