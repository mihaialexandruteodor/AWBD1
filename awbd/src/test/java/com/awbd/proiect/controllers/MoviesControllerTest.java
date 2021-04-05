package com.awbd.proiect.controllers;

import com.awbd.proiect.domain.Movie;
import com.awbd.proiect.services.MovieService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MoviesControllerTest {

    @Mock
    Model model;

    @Mock
    MovieService movieService;

    MoviesController moviesController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        moviesController = new MoviesController(movieService);
    }

    @Captor
    ArgumentCaptor<Movie> argumentCaptor;


    @Test
    public void showById() {
        Long id = 1l;
        Movie movieTest = new Movie();
        movieTest.setId(id);

        when(movieService.findById(id)).thenReturn(movieTest);

        String viewName = moviesController.showById(id.toString(), model);
        Assert.assertEquals("info", viewName);
        verify(movieService, times(1)).findById(id);

        //ArgumentCaptor<Movie> argumentCaptor = ArgumentCaptor.forClass(Movie.class);
        verify(model, times(1))
                .addAttribute(eq("movie"), argumentCaptor.capture() );

        Movie movieArg = argumentCaptor.getValue();
        Assert.assertEquals(movieArg.getId(), movieTest.getId() );

    }
}


