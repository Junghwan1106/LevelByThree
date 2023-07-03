package com.sparta.board.controller;

import com.sparta.board.dto.SignupRequestDto;
import com.sparta.board.dto.SignupResponseDto;
import com.sparta.board.dto.UserInfoDto;
import com.sparta.board.entity.UserRoleEnum;
import com.sparta.board.security.UserDetailsImpl;
import com.sparta.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


//    @PostMapping("/user/login")
//    public String login() {
//        return "login";
//    }
//

    @PostMapping("/user/signup")
    public SignupResponseDto signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new SignupResponseDto("회원 가입 실패", 400);
        }else{
            userService.signup(requestDto);
            return new SignupResponseDto("회원 가입 성공", 200);
        }
    }

    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }
}