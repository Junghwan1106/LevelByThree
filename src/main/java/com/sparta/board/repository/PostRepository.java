package com.sparta.board.repository;

import com.sparta.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    Optional<Post> findByIdAndUserId(Long id, Long userId);
}
