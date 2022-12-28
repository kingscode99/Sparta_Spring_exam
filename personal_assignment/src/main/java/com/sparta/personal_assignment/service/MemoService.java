package com.sparta.personal_assignment.service;

import com.sparta.personal_assignment.dto.IgnorePwDto;
import com.sparta.personal_assignment.dto.MemoRequestDto;
import com.sparta.personal_assignment.entity.Memo;
import com.sparta.personal_assignment.repository.MemoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    public List<IgnorePwDto> getMemos() {
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc();
        return memos.stream().map(IgnorePwDto::new).collect(Collectors.toList());
    }

    @Transactional
    public IgnorePwDto update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존제하지 않습니다.")
        );
        if(requestDto.getPw().equals(memo.getPw())){
            memo.update(requestDto);
        }
        return new IgnorePwDto(memo);
    }

    public IgnorePwDto findMemos(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () ->new IllegalArgumentException("아이디가 존제하지 않습니다.")
        );
        return new IgnorePwDto(memo);
    }

    @Transactional
    public String deleteMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존제하지 않습니다.")
        );
        if(requestDto.getPw().equals(memo.getPw())){
            memoRepository.deleteById(id);
            return "삭제가 완료 되었습니다.";
        }
        return "비밀번호가 일치하지 않습니다.";
    }
}
