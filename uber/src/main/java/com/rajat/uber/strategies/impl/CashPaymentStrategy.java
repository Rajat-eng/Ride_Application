package com.rajat.uber.strategies.impl;

import com.rajat.uber.entities.Driver;
import org.springframework.stereotype.Service;
import com.rajat.uber.entities.Payment;
import com.rajat.uber.entities.enums.PaymentStatus;
import com.rajat.uber.entities.enums.TransactionMethod;
import com.rajat.uber.repositories.PaymentRepository;
import com.rajat.uber.services.WalletService;
import com.rajat.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null,
                payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
