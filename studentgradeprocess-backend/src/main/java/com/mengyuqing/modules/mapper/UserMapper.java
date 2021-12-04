package com.mengyuqing.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengyuqing.modules.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Yuqing
 * @date 2021/11/13 22:11
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectByUser(@Param("userName") String userName, @Param("password") String password);

    User selectUserByStudentId(String userName);
}
