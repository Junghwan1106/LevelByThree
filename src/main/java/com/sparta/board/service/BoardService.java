package com.sparta.board.service;

import com.sparta.board.dto.MessageResponseDto;
import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.dto.PostResponseDto;
import com.sparta.board.entity.Post;
import com.sparta.board.jwt.JwtUtil;
import com.sparta.board.repository.BoardRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;

    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest req) {
        String token = jwtUtil.getJwtFromHeader(req);
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String username = claims.getSubject();
        if(!username.equals(requestDto.getUsername())){
            throw new IllegalArgumentException("사용자명이 일치하지 않습니다.");
        }
        Post post = new Post(requestDto,username);
        Post savePost = boardRepository.save(post);
        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest req) {
        Post post = findPost(id);
        validateTokenAndUser(requestDto,req);
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    public MessageResponseDto deletePost(Long id, PostRequestDto requestDto, HttpServletRequest req) {
        Post post = findPost(id);
        validateTokenAndUser(requestDto,req);
        boardRepository.delete(post);
        return new MessageResponseDto("삭제 완료", HttpStatus.OK.value());
    }

    private Post findPost(Long id){
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }

    private void validateTokenAndUser(PostRequestDto requestDto, HttpServletRequest req){
        String token = jwtUtil.getJwtFromHeader(req);
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String username = claims.getSubject();
        if(!username.equals(requestDto.getUsername())){
            throw new IllegalArgumentException("사용자명이 일치하지 않습니다.");
        }
    }
}
