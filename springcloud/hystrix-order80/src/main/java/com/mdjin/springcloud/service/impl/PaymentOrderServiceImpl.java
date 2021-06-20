package com.mdjin.springcloud.service.impl;

import com.mdjin.springcloud.service.PaymentOrderService;
import org.springframework.stereotype.Component;

/**
 * @author jinmaodong
 * @date 2021/6/16
 * @since 1.0.0
 **/
@Component
public class PaymentOrderServiceImpl implements PaymentOrderService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "fallback for paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "fallback for paymentInfo_Timeut";
    }
}
