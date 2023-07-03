package com.sparta.board.controller;

import com.sparta.board.dto.DeleteDto;
import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.dto.PostResponseDto;
import com.sparta.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //html 따로 반환하지 않기 때문에 RestController 사용
@RequestMapping("/api")
public class BoardController {

    private  final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }
    @PostMapping("/board") // 게시글 작성
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        return boardService.createPost(requestDto);
    }

    @GetMapping("/board") //전체 게시글 조회
    public List<PostResponseDto> getPosts(){
        return boardService.getPosts();
    }

    @GetMapping("/board/{id}") // 선택 게시글 조회
    public PostResponseDto getPost(@PathVariable Long id){
        return boardService.getPost(id);
    }

    @PutMapping("/board/{id}") // 게시글 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return boardService.updatePost(id, requestDto);
    }

    @DeleteMapping("/board/{id}") //게시글 삭제
    public DeleteDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return boardService.deletePost(id, requestDto);
    }
}
