package util;

import org.junit.Test;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;

public class ValidatorActivityNameTest {

    @Test(expected = ValidateException.class)
    public void testActivityName1() {
        String input = "";
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }

    @Test(expected = ValidateException.class)
    public void testActivityName2() {
        String input = "         ";
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }

    @Test(expected = ValidateException.class)
    public void testActivityName3() {
        String input = "Activity".repeat(50);
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }

    @Test(expected = ValidateException.class)
    public void testActivityName4() {
        String input = "кирилицаlatin";
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }

    @Test(expected = ValidateException.class)
    public void testActivityName5() {
        String input = "numbers123";
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }

    @Test(expected = ValidateException.class)
    public void testActivityName6() {
        String input = "Activity   Java";
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }

    @Test(expected = ValidateException.class)
    public void testActivityName7() {
        String input = "Unexpected123@@";
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }

    @Test
    public void testActivityName8() {
        String input = "activity";
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }

    @Test
    public void testActivityName9() {
        String input = "активность";
        Validator.validate(input, Validation.ACTIVITY_NAME);
    }
}
