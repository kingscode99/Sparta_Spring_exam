package com.sparta.personal_assignment.service;

import com.sparta.personal_assignment.dto.LoginRequestDto;
import com.sparta.personal_assignment.dto.SignupRequsetDto;
import com.sparta.personal_assignment.entity.User;
import com.sparta.personal_assignment.entity.UserRoleEnum;
import com.sparta.personal_assignment.jwt.JwtUtil;
import com.sparta.personal_assignment.repository.UserRepository;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final PasswordEncoder passwordEncoder;
    public String signup(@Valid SignupRequsetDto SignupRequsetDto) {
        String username = SignupRequsetDto.getUsername();
        String password = passwordEncoder.encode(SignupRequsetDto.getPassword());
        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (SignupRequsetDto.isAdmin()) {
            if (!SignupRequsetDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
        return "가입 완료";
    }

    @Transactional(readOnly = true)
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();
        User user = userRepository.findByUsername(username).orElseThrow(
                ()->new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return "로그인 성공";
    }


}
