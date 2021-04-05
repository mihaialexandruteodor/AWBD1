package com.awbd.proiect.services;

import com.awbd.proiect.domain.Movie;
import com.awbd.proiect.repositories.MovieRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class MovieServiceTest {

    MovieService movieService;

    @Mock
    MovieRepository movieRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        movieService = new MovieServiceImpl(movieRepository);
    }

    @Test
    public void findMovies() {
        List<Movie> moviesRet = new ArrayList<Movie>();
        Movie movie = new Movie();
        movie.setCategories(null);
        movie.setId(4L);
        //movie.setCode("TEST");
        moviesRet.add(movie);


        when(movieRepository.findAll()).thenReturn(moviesRet);
        List<Movie> movies = movieService.findAll();
        assertEquals(movies.size(), 1);
        verify(movieRepository, times(1)).findAll();
    }
}
