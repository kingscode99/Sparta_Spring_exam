package com.sparta.personal_assignment.service;

import com.sparta.personal_assignment.dto.UserRequsetDto;
import com.sparta.personal_assignment.entity.User;
import com.sparta.personal_assignment.jwt.JwtUtil;
import com.sparta.personal_assignment.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    public String createUser(@Valid UserRequsetDto requsetDto) {
        User user = new User(requsetDto);
        userRepository.save(user);
        return "가입 완료";
    }

    @Transactional(readOnly = true)
    public String signin(UserRequsetDto requsetDto, HttpServletResponse response) {
        String username = requsetDto.getUsername();
        String password = requsetDto.getPassword();
        User user = userRepository.findByUsername(username).orElseThrow(
                ()->new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        return "로그인 성공";
    }


}
