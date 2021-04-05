package com.awbd.proiect.repositories;

import com.awbd.proiect.domain.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {
   // @Query("select m from Movie m where m.studio.id = ?1")
   // List<Movie> findByStudio(Long studioId);

    @Query("select m from Movie m where m.name = :name")
    List<Movie> movieRepository(@Param("name") String movieName);

}
