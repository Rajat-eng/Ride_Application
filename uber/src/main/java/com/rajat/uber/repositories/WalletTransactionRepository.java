package com.rajat.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rajat.uber.entities.WalletTransaction;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

}
