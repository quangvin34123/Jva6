package com.example.duanjava6.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.duanjava6.Entity.Payment;

import java.util.List;

@Repository
public interface PaymentDAO extends JpaRepository<Payment, Integer> {
    List<Payment> findByOrderOrderId(Integer orderId);
    List<Payment> findByPaymentStatus(Payment.PaymentStatus status);
}
