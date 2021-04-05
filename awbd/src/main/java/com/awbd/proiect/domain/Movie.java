package com.awbd.proiect.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Setter
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int release_year;
    private Long price;

    @Min(value=100, message ="min price 100")
    private Double reservePrice;

    private Boolean restored;

    @OneToOne(mappedBy = "movie",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Info info;


    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "category",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",
                    referencedColumnName = "id"))
    private List<Category> categories;

    public void removeCategory(Category category) {
        category.getMovies().remove(this);
        categories.remove(category);
    }
}
