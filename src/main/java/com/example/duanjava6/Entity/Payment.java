package com.example.duanjava6.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "payment_method", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_status", length = 20)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.Pending;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate = LocalDateTime.now();

    public enum PaymentMethod {
        CreditCard, PayPal, CashOnDelivery
    }

    public enum PaymentStatus {
        Pending, Completed, Failed
    }
}
