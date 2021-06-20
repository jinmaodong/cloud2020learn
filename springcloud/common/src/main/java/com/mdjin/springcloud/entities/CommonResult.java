package com.mdjin.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Hashtable;
import java.util.Random;
import java.util.UUID;

/**
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 服务端口
     */
    private String serverPort;

    public CommonResult(Integer code,String message){
        this(code,message,null,null);
    }

}
