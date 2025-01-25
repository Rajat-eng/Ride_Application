package com.rajat.uber.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // join with id in User table
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;
}
