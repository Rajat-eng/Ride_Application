package com.rajat.uber.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rajat.uber.entities.WalletTransaction;
import com.rajat.uber.repositories.WalletTransactionRepository;
import com.rajat.uber.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionImpl implements WalletTransactionService {
     private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
