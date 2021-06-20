package com.mdjin.springcloud.dao;

import com.mdjin.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jinmaodong
 * @date 2021/6/12
 * @since 1.0.0
 **/
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id")long id);

    List<Payment> getAllPayment();
}
