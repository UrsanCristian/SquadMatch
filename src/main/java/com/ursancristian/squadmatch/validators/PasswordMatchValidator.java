package com.ursancristian.squadmatch.validators;

import com.ursancristian.squadmatch.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserDTO user = (UserDTO) obj;
        System.out.println(user.getPassword() + " " + user.getMatchingPassword());
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
