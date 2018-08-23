package uk.dioxic.ccpt.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import uk.dioxic.ccpt.validator.LuhnValidator;

public class LuhnValidatorTest {

    @Test
    public void isValid() {
        LuhnValidator validator = new LuhnValidator();
        Assertions.assertThat(validator.isValid("12345674", null)).as("valid cc").isTrue();
    }

    @Test
    public void isInvalid() {
        LuhnValidator validator = new LuhnValidator();
        Assertions.assertThat(validator.isValid("123456745", null)).as("invalid cc").isFalse();
    }
}
