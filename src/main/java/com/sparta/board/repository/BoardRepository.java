package com.sparta.board.repository;

import com.sparta.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
}
