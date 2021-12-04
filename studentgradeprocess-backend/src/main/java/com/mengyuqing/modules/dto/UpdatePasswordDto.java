package com.mengyuqing.modules.dto;

import lombok.Data;

/**
 * @author Yuqing
 * @date 2021/11/24 18:02
 */
@Data
public class UpdatePasswordDto {

    private String userName;

    private String oldPassWord;

    private String newPassWord;
}
