package util;

import org.junit.Test;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;

public class ValidatorPhoneTest {

    @Test(expected = ValidateException.class)
    public void testPhone1() {
        String input = "";
        Validator.validate(input, Validation.PHONE);
    }

    @Test(expected = ValidateException.class)
    public void testPhone2() {
        String input = "          ";
        Validator.validate(input, Validation.PHONE);
    }

    @Test(expected = ValidateException.class)
    public void testPhone3() {
        String input = "123456789";
        Validator.validate(input, Validation.PHONE);
    }

    @Test(expected = ValidateException.class)
    public void testPhone4() {
        String input = "12345678900";
        Validator.validate(input, Validation.PHONE);
    }

    @Test(expected = ValidateException.class)
    public void testPhone5() {
        String input = "123456789O";
        Validator.validate(input, Validation.PHONE);
    }

    @Test(expected = ValidateException.class)
    public void testPhone6() {
        String input = "+123456789";
        Validator.validate(input, Validation.PHONE);
    }

    @Test(expected = ValidateException.class)
    public void testPhone7() {
        String input = "aaaaaaaaaa";
        Validator.validate(input, Validation.PHONE);
    }

    @Test(expected = ValidateException.class)
    public void testPhone8() {
        String input = "1234567 90";
        Validator.validate(input, Validation.PHONE);
    }

    @Test
    public void testPhone9() {
        String input = "0952462878";
        Validator.validate(input, Validation.PHONE);
    }

    @Test
    public void testPhone10() {
        String input = "0000000000";
        Validator.validate(input, Validation.PHONE);
    }

}
