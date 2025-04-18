package com.puj.cinemapp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String nationality;
    private String biography;
    private String photoUrl;
}
