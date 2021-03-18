package com.awbd.lab3.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns =@JoinColumn(name="category_id",referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name="product_id",referencedColumnName="id"))
    private List<Product> products;

}
