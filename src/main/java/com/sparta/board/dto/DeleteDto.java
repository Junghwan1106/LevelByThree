package com.sparta.board.dto;

import lombok.Getter;

@Getter
public class DeleteDto {
    private String msg;

    public DeleteDto(String msg){
        this.msg = msg;
    }
}
