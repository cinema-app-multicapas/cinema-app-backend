package com.puj.cinemapp.service;

import com.puj.cinemapp.dto.MovieDTO;
import com.puj.cinemapp.model.Director;
import com.puj.cinemapp.model.Movie;
import com.puj.cinemapp.repository.DirectorRepository;
import com.puj.cinemapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MovieDTO getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public MovieDTO createMovie(MovieDTO dto) {
        Movie movie = convertToEntity(dto);
        return convertToDTO(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Long id, MovieDTO dto) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setTitle(dto.getTitle());
            movie.setReleaseYear(dto.getReleaseYear());
            movie.setGenre(dto.getGenre());
            movie.setSynopsis(dto.getSynopsis());
            movie.setDuration(dto.getDuration());
            movie.setPosterUrl(dto.getPosterUrl());

            Director director = directorRepository.findById(dto.getDirectorId()).orElse(null);
            movie.setDirector(director);

            return convertToDTO(movieRepository.save(movie));
        }
        return null;
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    private MovieDTO convertToDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setGenre(movie.getGenre());
        dto.setSynopsis(movie.getSynopsis());
        dto.setDuration(movie.getDuration());
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setDirectorId(movie.getDirector() != null ? movie.getDirector().getId() : null);
        return dto;
    }

    private Movie convertToEntity(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setGenre(dto.getGenre());
        movie.setSynopsis(dto.getSynopsis());
        movie.setDuration(dto.getDuration());
        movie.setPosterUrl(dto.getPosterUrl());

        Director director = directorRepository.findById(dto.getDirectorId()).orElse(null);
        movie.setDirector(director);

        return movie;
    }
}
