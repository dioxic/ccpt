package uk.dioxic.ccpt.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import uk.dioxic.ccpt.model.Card;
import uk.dioxic.ccpt.service.LuhnService;

@Deprecated
@Component("beforeCreateCardValidator")
public class CardValidator implements Validator {

    @Autowired
    private LuhnService luhnService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Card.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Card card = (Card) o;
        if (card.getName() == null || card.getName().trim().length() == 0) {
            errors.rejectValue("name", "name.empty");
        }

        if (card.getLimit() == null) {
            errors.rejectValue("limit", "limit.empty");
        }

        if (card.getBalance() == null) {
            errors.rejectValue("balance", "balance.empty");
        }

        if (!luhnService.isValid(card.getNumber())) {
            errors.rejectValue("number", "number.invalid");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
