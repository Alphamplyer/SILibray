package com.library.client.utils.validator;

import com.library.client.model.UserRegistration;
import com.library.client.utils.validator.customAnnotation.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
    implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserRegistration user = (UserRegistration) obj;
        return user.getPassword().equals(user.getPasswordComfimation());
    }
}