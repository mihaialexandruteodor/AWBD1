package com.awbd.lab3.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Double reservePrice;

    @OneToOne
    private Info info;


    @ManyToOne
    private Participant seller;

    @OneToMany(mappedBy = "product")
    private List<Bid> bids;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

}
