package com.rajat.uber.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor  // ✅ Explicitly add a no-args constructor
@AllArgsConstructor  // ✅ Ensure full constructor exists for @Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Double balance;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WalletTransaction> transactions;
}
