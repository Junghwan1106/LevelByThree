package com.sparta.board.service;

import com.sparta.board.dto.DeleteDto;
import com.sparta.board.dto.PostRequestDto;
import com.sparta.board.dto.PostResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        //RequestDto->Entity
        Board board = new Board(requestDto);
        //DB 저장
        Board savePost = boardRepository.save(board);
        //Entity->ResponseDto
        return new PostResponseDto(savePost);
    }

    public List<PostResponseDto> getPosts() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).toList();

    }

    public PostResponseDto getPost(Long id) {
        Board board = findPost(id);
        return new PostResponseDto(board);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Board board = findPost(id);
//        board.checkPassword(requestDto.getPassword());
        if(requestDto.getPassword().equals(board.getPassword())){
            board.update(requestDto);
        }else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new PostResponseDto(board);
    }

    public DeleteDto deletePost(Long id, PostRequestDto requestDto) {
        Board board = findPost(id);
//        board.checkPassword(requestDto.getPassword());
        String msg;
        if(requestDto.getPassword().equals(board.getPassword())){
            boardRepository.delete(board);
            msg = "삭제 완료";
        }else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new DeleteDto(msg);
    }

    private Board findPost(Long id){
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }

//    private Board checkPassword(PostRequestDto requestDto){
//        if (requestDto.getPassword().equals(board.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//    }

}
