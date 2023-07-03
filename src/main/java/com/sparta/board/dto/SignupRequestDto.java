package com.sparta.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Pattern(regexp = "[a-z0-9]{4,10}", message = "이름은 4자 이상, 10자 이하로 알파벳 소문자, 숫자로 설정해주세요.")
    private String username;
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]{8,15}", message = "비밀번호는 8자 이상, 15자 이하로 알파벳 대소문자, 숫자로 설정해주세요.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
