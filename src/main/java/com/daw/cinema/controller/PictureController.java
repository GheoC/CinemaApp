package com.daw.cinema.controller;

import com.daw.cinema.service.MoviePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PictureController {
    private final MoviePictureService moviePictureService;

    @PostMapping("/api/v1/pictures")
    @PreAuthorize("hasRole('ADMIN')")
    public void savePicture(@RequestParam("picture") MultipartFile file) throws IOException {
        moviePictureService.uploadPicture(file);
    }

    @GetMapping("/api/v1/pictures/{name}")
    public byte[] getImageByName(@PathVariable("name") String name) {
        return moviePictureService.findImageByName(name);
    }
}
