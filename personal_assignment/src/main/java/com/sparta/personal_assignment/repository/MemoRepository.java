package com.sparta.personal_assignment.repository;

import com.sparta.personal_assignment.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc();

    List<Memo> findByUserIdOrderByModifiedAt(Long id);
}