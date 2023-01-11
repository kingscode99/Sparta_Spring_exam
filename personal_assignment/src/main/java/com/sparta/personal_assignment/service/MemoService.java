package com.sparta.personal_assignment.service;

import com.sparta.personal_assignment.dto.MemoResponseDto;
import com.sparta.personal_assignment.dto.MemoRequestDto;
import com.sparta.personal_assignment.entity.Memo;
import com.sparta.personal_assignment.entity.User;
import com.sparta.personal_assignment.jwt.JwtUtil;
import com.sparta.personal_assignment.repository.MemoRepository;

import com.sparta.personal_assignment.repository.UserRepository;
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
    public Memo createMemo(MemoRequestDto requestDto, User user) {
        System.out.println("ProductService.createProduct");
        System.out.println("user.getUsername() = " + user.getUsername());

        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        return memoRepository.saveAndFlush(new Memo(requestDto, user.getId()));
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
    public MemoResponseDto update(Long id, MemoRequestDto requestDto, User user) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존제하지 않습니다.")
        );
        if(user.getId().equals(memo.getUserId())){
            memo.update(requestDto);
        }
        return new MemoResponseDto(memo, user.getUsername());
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
    public String deleteMemo(Long id, User user) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존제하지 않습니다.")
        );
        if(user.getId().equals(memo.getUserId())){
            memoRepository.deleteById(id);
        }
        return "삭제가 완료 되었습니다.";
    }
}