package com.awbd.lab7.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;
    private Long timestamp;

    @ManyToOne
    private Participant bidder;

    @ManyToOne
    private Product product;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

}
