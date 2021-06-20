package com.mdjin.springcloud.constant;

/**
 * 交互状态枚举类
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
public enum StatusConstant {
    STATUS_404(404),
    STATUS_500(500),
    STATUS_200(200);

    private Integer code;

    public Integer code(){
        return code;
    }

    private StatusConstant(Integer code ){
        this.code = code;
    }
}
