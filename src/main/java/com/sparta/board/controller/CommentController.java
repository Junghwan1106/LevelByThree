package com.sparta.board.controller;

import java.util.concurrent.RejectedExecutionException;

import com.sparta.board.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.board.dto.ApiResponseDto;
import com.sparta.board.dto.CommentRequestDto;
import com.sparta.board.dto.CommentResponseDto;
import com.sparta.board.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
//    @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto
    @PostMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

//        CommentResponseDto result = commentService.createComment(requestDto, userDetails.getUser());
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return  ResponseEntity.ok().body(commentService.createComment(id, commentRequestDto, userDetails.getUser()));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<ApiResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        try {
            CommentResponseDto result = commentService.updateComment(id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(result);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            commentService.deleteComment(id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}