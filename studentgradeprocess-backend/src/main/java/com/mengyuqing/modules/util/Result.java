/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.mengyuqing.modules.util;

import java.io.Serializable;

/**
 * @author Yuqing
 * @date 2021/10/26 0:45
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int INTERNAL_SERVER_ERROR = 500;
    /**
     * 0 represent sucess
     */
    private int code = 0;
    /**
     * 消息内容
     */
    private String msg = "success";
    /**
     * 响应数据
     */
    private T data;

    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success(){
        return code == 0 ? true : false;
    }

    public Result<T> error() {
        this.code = INTERNAL_SERVER_ERROR;
        this.msg = "服务器内部异常";
        return this;
    }

    public Result<T> error(int code) {
        this.code = code;
        this.msg = "服务器内部异常";
        return this;
    }

    public Result<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<T> error(String msg) {
        this.code = INTERNAL_SERVER_ERROR;
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return null;
    }
}
