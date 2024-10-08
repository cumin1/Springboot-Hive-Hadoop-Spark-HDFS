package com.hadoop.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ResultBean<T> {
    private int statusCode;
    private String message;
    private T data;

    // 默认构造方法
    public ResultBean() {
    }

    // 带所有字段的构造方法
    public ResultBean(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


}
