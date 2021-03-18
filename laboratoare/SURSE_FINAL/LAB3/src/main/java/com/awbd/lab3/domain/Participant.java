package com.awbd.lab3.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;


    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    @OneToMany(mappedBy = "bidder")
    private List<Bid> bids;


}
