package com.sparta.board.service;
import java.util.concurrent.RejectedExecutionException;

import com.sparta.board.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.board.dto.CommentRequestDto;
import com.sparta.board.dto.CommentResponseDto;
import com.sparta.board.entity.Comment;
import com.sparta.board.entity.Post;
import com.sparta.board.entity.User;
import com.sparta.board.entity.UserRoleEnum;
import com.sparta.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
//    private final PostService postService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(Long id,CommentRequestDto commentRequestDto, User user) {
//        Post post = postService.findPost(requestDto.getPostId());
//        Comment comment = new Comment(requestDto.getBody());
//        comment.setUser(user);
//        comment.setPost(post);
//
//        var savedComment = commentRepository.save(comment);
//
//        return new CommentResponseDto(savedComment);

        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("찾을 수 없는 게시글 입니다."));

        Comment comment = commentRepository.save(new Comment(commentRequestDto, post, user));

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow();

        // 요청자가 운영자 이거나 댓글 작성자(post.user) 와 요청자(user) 가 같은지 체크
        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !comment.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }

        commentRepository.delete(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow();

        // 요청자가 운영자 이거나 댓글 작성자(post.user) 와 요청자(user) 가 같은지 체크
        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !comment.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }

        comment.setBody(requestDto.getBody());

        return new CommentResponseDto(comment);
    }

}