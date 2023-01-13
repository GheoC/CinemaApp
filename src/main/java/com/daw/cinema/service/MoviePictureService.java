package com.daw.cinema.service;

import com.daw.cinema.entity.MoviePicture;
import com.daw.cinema.exception.exceptions.ResourceNotFoundException;
import com.daw.cinema.repository.MoviePictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MoviePictureService {

    private final MoviePictureRepository moviePictureRepository;

    public MoviePicture uploadPicture(MultipartFile imageFile) throws IOException {
        MoviePicture moviePicture = new MoviePicture();
        moviePicture.setName(imageFile.getOriginalFilename());
        moviePicture.setPicture(Base64.getEncoder().encode(imageFile.getBytes()));

        return moviePictureRepository.save(moviePicture);
    }

    public byte[] findImageByName(String name) {
        MoviePicture moviePicture = moviePictureRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Picture not found"));
        return moviePicture.getPicture();
    }

    @Transactional
    public void deleteByImageName(String imgName){
        moviePictureRepository.deleteByName(imgName);
    }
}
