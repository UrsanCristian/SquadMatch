package com.ursancristian.squadmatch.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

//@Target({TYPE, ANNOTATION_TYPE})
//@Retention(RUNTIME)
//@Constraint(validatedBy = PasswordMatchValidator.class)
//@Documented
@Documented
@Constraint(validatedBy = PasswordMatchValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
    String message() default "Passwords don't match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
