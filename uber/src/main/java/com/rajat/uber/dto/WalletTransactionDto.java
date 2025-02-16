package com.rajat.uber.dto;

import java.time.LocalDateTime;
import com.rajat.uber.entities.enums.TransactionMethod;
import com.rajat.uber.entities.enums.TransactionType;

public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto ride;

    private String transactionId;

    private WalletDto wallet;

    private LocalDateTime timeStamp;
}
