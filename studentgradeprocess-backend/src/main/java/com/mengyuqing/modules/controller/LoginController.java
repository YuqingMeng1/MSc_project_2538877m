package com.mengyuqing.modules.controller;

import com.mengyuqing.modules.dto.LoginDto;
import com.mengyuqing.modules.dto.UpdatePasswordDto;
import com.mengyuqing.modules.entity.User;
import com.mengyuqing.modules.service.LoginService;
import com.mengyuqing.modules.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yuqing
 * @date 2021/11/10 17:22
 */
@RequestMapping("login")
@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public Result<User> login(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto.getUserName(), loginDto.getPassWord());
    }

    @PutMapping
    public Result<Integer> updatePassWord(@RequestBody UpdatePasswordDto updatePasswordDto) {
        return loginService.updatePassword(updatePasswordDto);
    }
}
