package com.sparta.personal_assignment.controller;

import com.sparta.personal_assignment.dto.LoginRequestDto;
import com.sparta.personal_assignment.dto.SignupRequsetDto;
import com.sparta.personal_assignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    //userservice객체 생성
    private final UserService userService;

    //회원가입
    @PostMapping("/api/users/signup")
    public String signup(@RequestBody SignupRequsetDto signupRequsetDto){
        return userService.signup(signupRequsetDto);
    }

    @ResponseBody
    @PostMapping("/api/users/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}
