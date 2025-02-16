package com.rajat.uber.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rajat.uber.entities.User;
import com.rajat.uber.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
