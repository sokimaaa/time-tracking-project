package util;

import org.junit.Test;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;

public class ValidatorPasswordTest {

    @Test(expected = ValidateException.class)
    public void testPassword1() {
        String input = "";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test(expected = ValidateException.class)
    public void testPassword2() {
        String input = "          ";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test(expected = ValidateException.class)
    public void testPassword3() {
        String input = "Кириллица2";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test(expected = ValidateException.class)
    public void testPassword4() {
        String input = "withoutupper1";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test(expected = ValidateException.class)
    public void testPassword5() {
        String input = "Withoutnumb";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test(expected = ValidateException.class)
    public void testPassword6() {
        String input = "______123";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test(expected = ValidateException.class)
    public void testPassword7() {
        String input = "Password123PPPPPsssssssssaa";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test(expected = ValidateException.class)
    public void testPassword8() {
        String input = "May ch1";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test
    public void testPassword9() {
        String input = "Password91";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test
    public void testPassword10() {
        String input = "Password9%%!!$";
        Validator.validate(input, Validation.PASSWORD);
    }

    @Test
    public void testPassword11() {
        String input = "praY$$n1";
        Validator.validate(input, Validation.PASSWORD);
    }
}
