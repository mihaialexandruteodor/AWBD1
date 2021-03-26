package com.awbd.proiect.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

import java.util.List;


@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @Min(value=100, message ="min price 100")
    private Double reservePrice;

    private Boolean restored;

    @OneToOne(mappedBy = "product",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Info info;


    @ManyToOne
    private Participant seller;

    @OneToMany(mappedBy = "product")
    private List<Bid> bids;


    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",
                    referencedColumnName = "id"))
    private List<Category> categories;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;


    public void removeCategory(Category category) {
        category.getProducts().remove(this);
        categories.remove(category);
    }

}