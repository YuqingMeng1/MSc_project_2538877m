package com.mengyuqing.modules.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Yuqing
 * @date 2021/11/13 22:08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class User {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long studentInfoId;

    private String userName;

    private String passWord;

    private Date createTime;

    private Date updateTime;
}
