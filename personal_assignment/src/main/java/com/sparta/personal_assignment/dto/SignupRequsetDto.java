package com.sparta.personal_assignment.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignupRequsetDto {
    @NotBlank
    @Pattern(regexp = "(?=.+[0-9])(?=.+[a-z]).{4,10}", message = "username은 4~10자 소문자와 숫자를 사용하세요.")
    private String username;
    @NotBlank
    @Pattern(regexp = "(?=.+[0-9])(?=.+[a-zA-Z]).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자를 사용하세요.")
    private String password;
    private boolean admin = false;
    private String adminToken = "";
}