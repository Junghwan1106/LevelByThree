package com.sparta.board.repository;

import com.sparta.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.board.entity.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}