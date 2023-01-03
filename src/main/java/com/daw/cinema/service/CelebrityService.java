package com.daw.cinema.service;

import com.daw.cinema.entity.Celebrity;
import com.daw.cinema.repository.CelebrityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CelebrityService {
    private final CelebrityRepository celebrityRepository;

    public Celebrity getCelebrity(String name) {
        return celebrityRepository.findByName(name).orElseGet(() -> celebrityRepository.save(new Celebrity(name)));
    }
}
