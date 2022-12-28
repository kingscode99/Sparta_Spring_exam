package com.sparta.personal_assignment.controller;

import com.sparta.personal_assignment.dto.IgnorePwDto;
import com.sparta.personal_assignment.dto.MemoRequestDto;
import com.sparta.personal_assignment.entity.Memo;
import com.sparta.personal_assignment.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    //service 객체 생성
    private final MemoService memoService;


    //메모 테이블 만들기
    @PostMapping("/api/memos")
    public String createMemo(@RequestBody MemoRequestDto requestDto){
        return memoService.createMemo(requestDto);
    }

    //메모 보여주기
    @GetMapping("/api/memos")
    public List<IgnorePwDto> getMemos() {
        return memoService.getMemos();
    }

    //메모 수정하기
    @PutMapping("/api/memos/{id}")
    public IgnorePwDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        IgnorePwDto ignorePwDto = memoService.update(id, requestDto);
        System.out.println(ignorePwDto);
        return ignorePwDto;
    }

    //메모 조회하기
    @GetMapping("/api/memos/find/{id}")
    public IgnorePwDto findMemos(@PathVariable Long id){
        return memoService.findMemos(id);
    }

    //메모 삭제하기
    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.deleteMemo(id, requestDto);
    }
}
