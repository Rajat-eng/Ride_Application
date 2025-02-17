package com.rajat.uber.strategies;

import org.springframework.stereotype.Component;
import com.rajat.uber.entities.enums.PaymentMethod;
import com.rajat.uber.strategies.impl.CashPaymentStrategy;
import com.rajat.uber.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    // factory pattern
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
