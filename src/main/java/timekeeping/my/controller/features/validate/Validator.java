package timekeeping.my.controller.features.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The validator class provides methods to validate input's fields.
 * */
public final class Validator {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    /**
     * matches if all of these condition true
     * 1. At least 6 character but not more than 20
     * 2. At least one upper case character
     * 3. At least one number
     * 4. You might use a latin characters (doesn't match a Cyrillic),
     * number or special symbols (one of these _, @, $, !, %, *, ?, &)
     * */
    private static final String REGEX_PASSWORD_VALIDATE = "^(?=.*[A-Z])(?=.*\\d)[\\w@$!%*?&]{6,20}$";

    /**
     * might use only latin characters,
     * number or special symbols (one of these _, -, .)
     * login must contain at least one latin character
     * login must have at least 4 symbols and not more than 16
     * */
    private static final String REGEX_LOGIN_VALIDATE = "^(?=.*[a-zA-Z])[\\w-.]{4,16}$";

    /**
     * might use a latin character and number.
     * must end with a @gmail.com
     * */
    private static final String REGEX_EMAIL_VALIDATE = "^[a-zA-Z\\d]{4,20}@gmail\\.com$";

    /**
     * must be only latin or only cyrillic symbols
     * */
    private static final String REGEX_ACTIVITY_NAME_VALIDATE_1 = "^[a-zA-Z]{4,40}$";
    private static final String REGEX_ACTIVITY_NAME_VALIDATE_2 = "^[а-яА-Я]{4,40}$";

    /**
     * must contain 10 numbers
     * */
    private static final String REGEX_PHONE_VALIDATE = "^\\d{10}$";

    /**
     * validates input-string according to the chosen validation
     * @param input the string need to validate
     * @param validation the param of validation
     * @throws ValidateException if validate did not pass
     * */
    public static void validate(String input, Validation validation) throws ValidateException {
        switch (validation) {
            case ENTER_TIME:
                validateEnterTime(input);
                break;
            case DESCRIPTION:
                validateDescription(input);
                break;
            case ACTIVITY_NAME:
                validateActivityName(input);
                break;
            case PHONE:
                validatePhone(input);
                break;
            case EMAIL:
                validateEmail(input);
                break;
            case LOGIN:
                validateLogin(input);
                break;
            case PASSWORD:
                validatePassword(input);
                break;
            default:
                log.info("Unexpected validation.. Nothing was validated.. Provided validation ==> " + validation.name());
        }

        log.trace("Validator passed!!");
    }

    /**
     * entered time must be positive integer
     * */
    private static void validateEnterTime(String enterTime) throws ValidateException {
        try {
            int number = Integer.parseInt(enterTime);
            if(number <= 0)
                throw new NumberFormatException("Provided not positive integer");
        } catch (NumberFormatException e) {
            log.info("Entered not a integer or non-positive integer.. input ==> " + enterTime);
            throw new ValidateException("entered not a integer or non-positive integer");
        }
    }

    private static void validateDescription(String description) {
        // no matter what entered into description
    }

    private static void validateActivityName(String activityName) throws ValidateException {
        Pattern p1 = Pattern.compile(REGEX_ACTIVITY_NAME_VALIDATE_1);
        Pattern p2 = Pattern.compile(REGEX_ACTIVITY_NAME_VALIDATE_2);
        Matcher m1 = p1.matcher(activityName);
        Matcher m2 = p2.matcher(activityName);
        if(!m1.matches() && !m2.matches()) {
            log.info("Activity name does not match regex.. input ==> " + activityName);
            throw new ValidateException("does not match regex..");
        }
    }

    private static void validatePhone(String phone) throws ValidateException {
        Pattern p = Pattern.compile(REGEX_PHONE_VALIDATE);
        Matcher m = p.matcher(phone);
        if(!m.matches()) {
            log.info("Phone does not match regex.. input ==> " + phone);
            throw new ValidateException("does not match regex..");
        }
    }

    private static void validateEmail(String email) throws ValidateException {
        Pattern p = Pattern.compile(REGEX_EMAIL_VALIDATE);
        Matcher m = p.matcher(email);
        if(!m.matches()) {
            log.info("Email does not match regex.. input ==> " + email);
            throw new ValidateException("does not match regex..");
        }
    }

    private static void validateLogin(String login) throws ValidateException {
        Pattern p = Pattern.compile(REGEX_LOGIN_VALIDATE);
        Matcher m = p.matcher(login);
        if(!m.matches()) {
            log.info("Login does not match regex.. input ==> " + login);
            throw new ValidateException("does not match regex..");
        }
    }

    private static void validatePassword(String password) throws ValidateException {
        Pattern p = Pattern.compile(REGEX_PASSWORD_VALIDATE);
        Matcher m = p.matcher(password);
        if(!m.matches()) {
            log.info("Password does not match regex.. input ==> " + password);
            throw new ValidateException("does not match regex..");
        }
    }

    private Validator() {

    }

}
