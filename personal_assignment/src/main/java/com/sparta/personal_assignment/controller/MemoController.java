package com.sparta.personal_assignment.controller;

import com.sparta.personal_assignment.dto.MemoResponseDto;
import com.sparta.personal_assignment.dto.MemoRequestDto;
import com.sparta.personal_assignment.entity.Memo;
import com.sparta.personal_assignment.security.UserDetailsImpl;
import com.sparta.personal_assignment.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    //service 객체 생성
    private final MemoService memoService;


    //메모 테이블 만들기
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return memoService.createMemo(requestDto, userDetails.getUser());
    }

    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemos();
    }

    //메모 수정하기
    @PutMapping("/api/memos/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MemoResponseDto memoResponseDto = memoService.update(id, requestDto, userDetails.getUser());
        System.out.println(memoResponseDto);
        return memoResponseDto;
    }

    //메모 조회하기
    @GetMapping("/api/memos/{id}")
    public MemoResponseDto findMemos(@PathVariable Long id){
        return memoService.findMemos(id);
    }

    //메모 삭제하기
    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return memoService.deleteMemo(id, userDetails.getUser());
    }
}
