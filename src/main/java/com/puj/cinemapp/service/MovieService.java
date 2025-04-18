package com.puj.cinemapp.service;

import com.puj.cinemapp.dto.MovieDTO;
import com.puj.cinemapp.model.Director;
import com.puj.cinemapp.model.Movie;
import com.puj.cinemapp.repository.DirectorRepository;
import com.puj.cinemapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DirectorRepository directorRepository;

    // Obtener todas las películas
    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una película por su ID
    public MovieDTO getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);  // Mejorar manejo de excepciones si es necesario
    }

    // Crear una nueva película
    @Transactional
    public MovieDTO createMovie(MovieDTO dto) {
        Director director = directorRepository.findById(dto.getDirectorId())
                .orElseThrow(() -> new IllegalArgumentException("Director no encontrado"));

        Movie movie = convertToEntity(dto, director);
        return convertToDTO(movieRepository.save(movie));
    }

    // Actualizar una película existente
    @Transactional
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

            Director director = directorRepository.findById(dto.getDirectorId())
                    .orElseThrow(() -> new IllegalArgumentException("Director no encontrado"));
            movie.setDirector(director);

            return convertToDTO(movieRepository.save(movie));
        }
        return null;  // Podrías lanzar una excepción aquí si prefieres manejarlo de forma más estricta
    }

    // Eliminar una película
    @Transactional
    public void deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Película no encontrada");
        }
    }

    // Convertir Movie a MovieDTO
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

    // Convertir MovieDTO a Movie
    private Movie convertToEntity(MovieDTO dto, Director director) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setGenre(dto.getGenre());
        movie.setSynopsis(dto.getSynopsis());
        movie.setDuration(dto.getDuration());
        movie.setPosterUrl(dto.getPosterUrl());
        movie.setDirector(director);  // Asignar el director explícitamente

        return movie;
    }
}
