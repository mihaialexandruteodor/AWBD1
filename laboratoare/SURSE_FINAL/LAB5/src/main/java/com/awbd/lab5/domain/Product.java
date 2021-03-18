package com.awbd.lab5.domain;

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

    @OneToOne(mappedBy = "product",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Info info;


    @ManyToOne
    private Participant seller;

    @OneToMany(mappedBy = "product")
    private List<Bid> bids;

    @ManyToMany(mappedBy = "products",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> categories;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;


    public void removeCategory(Category category) {
        category.getProducts().remove(this);
        categories.remove(category);
    }
}
