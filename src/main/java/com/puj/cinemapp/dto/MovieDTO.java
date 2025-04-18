package com.puj.cinemapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    
    private Long id;
    private String title;
    private int releaseYear;
    private String genre;
    private String synopsis;
    private int duration;
    private String posterUrl;
    private Long directorId;

}
