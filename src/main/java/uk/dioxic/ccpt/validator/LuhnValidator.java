package uk.dioxic.ccpt.validator;

import uk.dioxic.ccpt.annotations.CardNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LuhnValidator implements ConstraintValidator<CardNumber, String> {

    @Override
    public void initialize(CardNumber contactNumber) {
    }

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext cxt) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

}