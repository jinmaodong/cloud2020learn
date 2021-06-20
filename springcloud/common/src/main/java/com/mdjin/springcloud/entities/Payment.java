package com.mdjin.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    /**
     * 代码ID
     */
    private long id;
    /**
     * 流水号
     */
    private String serial;

}
