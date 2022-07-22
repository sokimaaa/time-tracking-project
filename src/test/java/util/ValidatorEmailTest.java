package util;

import org.junit.Test;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;

public class ValidatorEmailTest {

    @Test(expected = ValidateException.class)
    public void testEmail1() {
        String input = "";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test(expected = ValidateException.class)
    public void testEmail2() {
        String input = "          ";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test(expected = ValidateException.class)
    public void testEmail3() {
        String input = "    @gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test(expected = ValidateException.class)
    public void testEmail4() {
        String input = "@gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test(expected = ValidateException.class)
    public void testEmail5() {
        String input = "cor@gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test(expected = ValidateException.class)
    public void testEmail6() {
        String input = "кирилица@gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test(expected = ValidateException.class)
    public void testEmail7() {
        String input = "st art23@gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test(expected = ValidateException.class)
    public void testEmail8() {
        String input = "mailing32_@gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test(expected = ValidateException.class)
    public void testEmail9() {
        String input = "anuther1@epam.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test
    public void testEmail10() {
        String input = "12312323@gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test
    public void testEmail11() {
        String input = "anuther123@gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }

    @Test
    public void testEmail12() {
        String input = "anuther@gmail.com";
        Validator.validate(input, Validation.EMAIL);
    }
}
