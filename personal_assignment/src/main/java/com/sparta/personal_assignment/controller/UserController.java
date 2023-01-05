package com.sparta.personal_assignment.controller;

import com.sparta.personal_assignment.dto.UserRequsetDto;
import com.sparta.personal_assignment.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    //userservice객체 생성
    private final UserService userService;

    //회원가입
    @PostMapping("/api/signup")
    public String createUsers(@RequestBody UserRequsetDto requsetDto){
        return userService.createUser(requsetDto);
    }

    @ResponseBody
    @PostMapping("/api/signin")
    public String signing(@RequestBody UserRequsetDto requsetDto, HttpServletResponse response){
        return userService.signin(requsetDto, response);
    }
}
