package com.sparta.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.board.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}