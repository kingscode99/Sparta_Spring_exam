package com.sparta.personal_assignment.service;

import com.sparta.personal_assignment.dto.MemoResponseDto;
import com.sparta.personal_assignment.dto.MemoRequestDto;
import com.sparta.personal_assignment.entity.Memo;
import com.sparta.personal_assignment.entity.User;
import com.sparta.personal_assignment.jwt.JwtUtil;
import com.sparta.personal_assignment.repository.MemoRepository;

import com.sparta.personal_assignment.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        Long userId;
        if(token != null){
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            userId = user.getId();
            Memo memo = new Memo(requestDto, userId);
            memoRepository.save(memo);
            return memo;
        }
        return null;
    }

    public List<MemoResponseDto> getMemos() {
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc();
        List<MemoResponseDto> responseDtos = new ArrayList<>();
        for(Memo memo: memos){
            User user = userRepository.findById(memo.getUserId()).orElseThrow(
                    ()-> new IllegalArgumentException("일치하는 사용자가 없습니다.")
            );
            MemoResponseDto responseDto = new MemoResponseDto(memo, user.getUsername());
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto, HttpServletRequest request) {
        //Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존제하지 않습니다.")
            );
            if(user.getId().equals(memo.getUserId())){
                memo.update(requestDto);
            }
            return new MemoResponseDto(memo, user.getUsername());
        }
        return null;
    }

    public MemoResponseDto findMemos(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () ->new IllegalArgumentException("아이디가 존제하지 않습니다.")
        );
        User user = userRepository.findById(memo.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("일치하는 사용자가 없습니다.")
        );
        return new MemoResponseDto(memo, user.getUsername());
    }

    @Transactional
    public String deleteMemo(Long id, HttpServletRequest request) {
        //Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("아이디가 존제하지 않습니다.")
            );
            if(user.getId().equals(memo.getUserId())){
                memoRepository.deleteById(id);
            }
            return "삭제가 완료 되었습니다.";
        }
        return null;
    }
}