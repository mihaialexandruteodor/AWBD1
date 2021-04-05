package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@Slf4j
public class ProductRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void findMovies() {
        List<Movie> movies = movieRepository.movieRepository("In the Mood for Love");
        assertTrue(movies.size() >= 2);
        log.info("findByStudio ...");
        movies.forEach(movie -> log.info(movie.getName()));
    }
}
