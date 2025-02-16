package com.rajat.uber.services;

import com.rajat.uber.entities.Payment;
import com.rajat.uber.entities.Ride;
import com.rajat.uber.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
