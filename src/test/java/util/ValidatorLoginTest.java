package util;

import org.junit.Test;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;

public class ValidatorLoginTest {

    @Test(expected = ValidateException.class)
    public void testLogin1() {
        String input = "кирилица123";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test(expected = ValidateException.class)
    public void testLogin2() {
        String input = "----____----";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test(expected = ValidateException.class)
    public void testLogin3() {
        String input = "Sоkimaaa";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test(expected = ValidateException.class)
    public void testLogin4() {
        String input = "TooooLongNicknameeeee";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test(expected = ValidateException.class)
    public void testLogin5() {
        String input = "user admin";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test(expected = ValidateException.class)
    public void testLogin6() {
        String input = "";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test(expected = ValidateException.class)
    public void testLogin7() {
        String input = "          ";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test(expected = ValidateException.class)
    public void testLogin8() {
        String input = "Sokima##";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test(expected = ValidateException.class)
    public void testLogin9() {
        String input = "99999999";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test
    public void testLogin10() {
        String input = "Sokimaaa93";
        Validator.validate(input, Validation.LOGIN);
    }

    @Test
    public void testLogin11() {
        String input = "..__Nick---";
        Validator.validate(input, Validation.LOGIN);
    }
}
