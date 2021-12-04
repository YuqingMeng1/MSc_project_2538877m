package com.mengyuqing.modules.service.impl;

import com.mengyuqing.modules.dto.UpdatePasswordDto;
import com.mengyuqing.modules.entity.User;
import com.mengyuqing.modules.mapper.UserMapper;
import com.mengyuqing.modules.service.LoginService;
import com.mengyuqing.modules.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author Yuqing
 * @date 2021/11/10 17:23
 */
@Service
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;

    public LoginServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result<User> login(String userName, String password) {
        Result<User> result = new Result();
        if ("admin".equals(userName) && "admin".equals(password)) {
            return result;
        }
        User user = userMapper.selectByUser(userName, password);
        if (!ObjectUtils.isEmpty(user)) {
            result.setData(user);
            return result;
        }
        return result.error("Incorrect username or password");
    }

    @Override
    public Result<Integer> updatePassword(UpdatePasswordDto updatePasswordDto) {
        Result<Integer> result = new Result();
        User userInfo = userMapper.selectUserByStudentId(updatePasswordDto.getUserName());
        if (ObjectUtils.isEmpty(userInfo)) {
            return result.error("not found student");
        }
        if (!userInfo.getPassWord().equals(updatePasswordDto.getOldPassWord())) {
            return result.error("old password error");
        }
        userInfo.setPassWord(updatePasswordDto.getNewPassWord());
        int sum = userMapper.updateById(userInfo);
        if (sum > 0){
            return result;
        } else {
            return result.error("update error");
        }
    }
}
