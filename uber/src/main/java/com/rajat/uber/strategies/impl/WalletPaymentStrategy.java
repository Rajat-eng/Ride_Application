package com.rajat.uber.strategies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rajat.uber.entities.Driver;
import com.rajat.uber.entities.Payment;
import com.rajat.uber.entities.Rider;
import com.rajat.uber.entities.enums.PaymentStatus;
import com.rajat.uber.entities.enums.TransactionMethod;
import com.rajat.uber.repositories.PaymentRepository;
import com.rajat.uber.services.WalletService;
import com.rajat.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;


    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null,
                payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driversCut, null, payment.getRide(),
                TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
