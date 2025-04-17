package com.puj.cinemapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.puj.cinemapp.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}