package com.sparta.personal_assignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserRequsetDto {
    @NotBlank
    @Pattern(regexp = "(?=.+[0-9])(?=.+[a-z]).{4,10}", message = "username은 4~10자 소문자와 숫자를 사용하세요.")
    private String username;
    @NotBlank
    @Pattern(regexp = "(?=.+[0-9])(?=.+[a-zA-Z]).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자를 사용하세요.")
    private String password;
}