package com.sparta.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SignupResponseDto {
    private String msg;
    private int status;
}
