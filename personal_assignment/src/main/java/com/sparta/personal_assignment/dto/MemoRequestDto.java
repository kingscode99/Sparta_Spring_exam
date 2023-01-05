package com.sparta.personal_assignment.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemoRequestDto {
    private String title;
    private String comment;
}
