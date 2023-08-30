package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> { // 게시물같은 경우는 글을 많이 쓸 수 있어서 Long으로 하는것이 좋다.
}
