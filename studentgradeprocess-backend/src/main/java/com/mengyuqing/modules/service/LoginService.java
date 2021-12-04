package com.mengyuqing.modules.service;

import com.mengyuqing.modules.dto.UpdatePasswordDto;
import com.mengyuqing.modules.entity.User;
import com.mengyuqing.modules.util.Result;

/**
 * @author Yuqing
 * @date 2021/11/10 17:23
 */
public interface LoginService {
    Result<User> login(String userName, String password);

    Result<Integer> updatePassword(UpdatePasswordDto updatePasswordDto);
}
