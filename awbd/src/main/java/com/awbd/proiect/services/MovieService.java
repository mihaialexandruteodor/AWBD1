package com.awbd.proiect.services;

import com.awbd.proiect.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long l);
    Movie save(Movie movie);
    void deleteById(Long id);

}
