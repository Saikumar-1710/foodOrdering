package com.restaurants.FastFoodShop.Service;

import java.util.List;
import java.util.Optional;

import com.restaurants.FastFoodShop.Entity.Payment;

public interface PaymentService {

    Payment savePayment(Payment payment);

    Payment updatePayment(Payment payment);

    void deletePayment(Integer id);

    Optional<Payment> getPaymentById(Integer id);

    Optional<Payment> getPaymentByOrderId(Integer orderId);

    List<Payment> getAllPayments();
}