package util;

import org.junit.Test;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;

public class ValidatorEnterTimeTest {

    @Test(expected = ValidateException.class)
    public void testEnterTime1() {
        String input = "1.2";
        Validator.validate(input, Validation.ENTER_TIME);
    }

    @Test(expected = ValidateException.class)
    public void testEnterTime2() {
        String input = "1.2a";
        Validator.validate(input, Validation.ENTER_TIME);
    }

    @Test(expected = ValidateException.class)
    public void testEnterTime3() {
        String input = "-2.3";
        Validator.validate(input, Validation.ENTER_TIME);
    }

    @Test(expected = ValidateException.class)
    public void testEnterTime4() {
        String input = "acbs";
        Validator.validate(input, Validation.ENTER_TIME);
    }

    @Test(expected = ValidateException.class)
    public void testEnterTime5() {
        String input = "!@#$";
        Validator.validate(input, Validation.ENTER_TIME);
    }

    @Test(expected = ValidateException.class)
    public void testEnterTime6() {
        String input = "-120";
        Validator.validate(input, Validation.ENTER_TIME);
    }

    @Test(expected = ValidateException.class)
    public void testEnterTime7() {
        String input = "0";
        Validator.validate(input, Validation.ENTER_TIME);
    }

    @Test(expected = ValidateException.class)
    public void testEnterTime8() {
        String input = "100.";
        Validator.validate(input, Validation.ENTER_TIME);
    }


    @Test
    public void testEnterTime9() {
        String input = "120";
        Validator.validate(input, Validation.ENTER_TIME);
    }
}
