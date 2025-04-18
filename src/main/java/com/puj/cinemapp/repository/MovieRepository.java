package com.puj.cinemapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.puj.cinemapp.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByDirectorId(Long directorId);
}