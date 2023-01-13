package com.daw.cinema.validation.discriminator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ValidEachMovieEventValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEachMovieEvent {
    String message() default "At least one of the elements is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
