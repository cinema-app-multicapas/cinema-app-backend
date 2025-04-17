package com.puj.cinemapp.controller;

import com.puj.cinemapp.dto.DirectorDTO;
import com.puj.cinemapp.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
@CrossOrigin(origins = "*")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public List<DirectorDTO> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public DirectorDTO getDirectorById(@PathVariable Long id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping
    public DirectorDTO createDirector(@RequestBody DirectorDTO directorDTO) {
        return directorService.createDirector(directorDTO);
    }

    @PutMapping("/{id}")
    public DirectorDTO updateDirector(@PathVariable Long id, @RequestBody DirectorDTO directorDTO) {
        return directorService.updateDirector(id, directorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
    }
}
