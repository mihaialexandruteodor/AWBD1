package com.awbd.proiect.services;

import com.awbd.proiect.domain.Category;
import com.awbd.proiect.domain.Movie;
import com.awbd.proiect.exceptions.ResourceNotFoundException;
import com.awbd.proiect.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll(){
        List<Movie> movies = new LinkedList<>();
        movieRepository.findAll().iterator().forEachRemaining(movies::add);
        return movies;
    }

    @Override
    public Movie findById(Long l) {
        Optional<Movie> movieOptional = movieRepository.findById(l);
        if (!movieOptional.isPresent()) {
            throw new ResourceNotFoundException("movie " + l + " not found");
        }
        return movieOptional.get();
    }

    @Override
    public Movie save(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (!movieOptional.isPresent()) {
            throw new RuntimeException("Movie not found!");
        }
        Movie movie = movieOptional.get();
        List<Category> categories = new LinkedList<Category>();
        movie.getCategories().iterator().forEachRemaining(categories::add);

        for (Category category: categories
                ) {
            movie.removeCategory(category);
        }

        movieRepository.save(movie);
        movieRepository.deleteById(id);

    }

}
