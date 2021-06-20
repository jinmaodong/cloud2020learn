package com.mdjin.springcloud.service.impl;

import com.mdjin.springcloud.dao.PaymentDao;
import com.mdjin.springcloud.entities.Payment;
import com.mdjin.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentDao.getAllPayment();
    }
}
