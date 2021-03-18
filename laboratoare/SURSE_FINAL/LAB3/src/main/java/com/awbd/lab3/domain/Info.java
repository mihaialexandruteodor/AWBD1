package com.awbd.lab3.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte[] imagine;
    private String description;

    @OneToOne
    private Product product;


}
