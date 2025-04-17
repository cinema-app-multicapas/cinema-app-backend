package com.puj.cinemapp.service;

import com.puj.cinemapp.dto.DirectorDTO;
import com.puj.cinemapp.model.Director;
import com.puj.cinemapp.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    public List<DirectorDTO> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DirectorDTO getDirectorById(Long id) {
        return directorRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public DirectorDTO createDirector(DirectorDTO dto) {
        Director director = convertToEntity(dto);
        return convertToDTO(directorRepository.save(director));
    }

    public DirectorDTO updateDirector(Long id, DirectorDTO dto) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (optionalDirector.isPresent()) {
            Director director = optionalDirector.get();
            director.setName(dto.getName());
            director.setBirthdate(dto.getBirthdate());
            director.setNationality(dto.getNationality());
            director.setBiography(dto.getBiography());
            director.setPhotoUrl(dto.getPhotoUrl());

            return convertToDTO(directorRepository.save(director));
        }
        return null;
    }

    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }

    private DirectorDTO convertToDTO(Director director) {
        DirectorDTO dto = new DirectorDTO();
        dto.setId(director.getId());
        dto.setName(director.getName());
        dto.setBirthdate(director.getBirthdate());
        dto.setNationality(director.getNationality());
        dto.setBiography(director.getBiography());
        dto.setPhotoUrl(director.getPhotoUrl());
        return dto;
    }

    private Director convertToEntity(DirectorDTO dto) {
        Director director = new Director();
        director.setName(dto.getName());
        director.setBirthdate(dto.getBirthdate());
        director.setNationality(dto.getNationality());
        director.setBiography(dto.getBiography());
        director.setPhotoUrl(dto.getPhotoUrl());
        return director;
    }
}
