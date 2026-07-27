package com.example.sign_up.domain.repository;

import com.example.sign_up.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
