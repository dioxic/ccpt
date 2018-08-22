package uk.dioxic.ccpt.validator;

import uk.dioxic.ccpt.annotations.CardNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LuhnValidator implements ConstraintValidator<CardNumber, Integer> {

    @Override
    public void initialize(CardNumber contactNumber) {
    }

    @Override
    public boolean isValid(Integer cardField, ConstraintValidatorContext cxt) {
        return true;
    }

}