package com.puj.cinemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.puj.cinemapp.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}