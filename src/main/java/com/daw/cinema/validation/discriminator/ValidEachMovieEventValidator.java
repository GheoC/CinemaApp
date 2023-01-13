package com.daw.cinema.validation.discriminator;

import com.daw.cinema.dto.MovieEventDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.List;

public class ValidEachMovieEventValidator implements ConstraintValidator<ValidEachMovieEvent, List<MovieEventDto>> {
    @Override
    public boolean isValid(List<MovieEventDto> movieEvents, ConstraintValidatorContext context) {
        List<MovieEventDto> notValidEvents = movieEvents.stream().filter(movieEventDto -> (movieEventDto.getId() != null
                || movieEventDto.getStatus() != null || movieEventDto.getPlayMovieDateTime().isBefore(LocalDateTime.now()))).toList();

        return notValidEvents.size() == 0;
    }
}
