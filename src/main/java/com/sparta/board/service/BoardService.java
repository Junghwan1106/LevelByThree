package com.sparta.board.service;

import com.sparta.board.dto.DeleteDto;
import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.dto.PostResponseDto;
import com.sparta.board.entity.Board;
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
            return null;
        }
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String username = claims.getSubject();
        if(username.equals(requestDto.getUsername())){
            Board board = new Board(requestDto,username);
            Board savePost = boardRepository.save(board);
            return new PostResponseDto(savePost);
        }
        throw new IllegalArgumentException("사용자명이 일치하지 않습니다.");
    }

    public List<PostResponseDto> getPosts() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long id) {
        Board board = findPost(id);
        return new PostResponseDto(board);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest req) {
        Board board = findPost(id);
        validateTokenAndUser(requestDto,req);
        board.update(requestDto);
        return new PostResponseDto(board);
    }

    public DeleteDto deletePost(Long id, PostRequestDto requestDto, HttpServletRequest req) {
        Board board = findPost(id);
        validateTokenAndUser(requestDto,req);
        boardRepository.delete(board);
        return new DeleteDto("삭제 완료", HttpStatus.OK.value());
    }

    private Board findPost(Long id){
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
