package timekeeping.my.controller.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.exception.PasswordEqualityException;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.UserService;
import timekeeping.my.service.exception.InsertEntityException;
import timekeeping.my.service.impl.UserServiceImpl;
import timekeeping.my.view.field.FieldConstant;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;

/**
 * command to redirect
 * 1) to the home page if all data entered successfully and such user does not exist
 * 2) to the register page if entered data invalid or such user exist
 * sets error messages if needed
 * */
public class RegisterCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(RegisterCommand.class);
    private final UserService service;

    public RegisterCommand() {
        this.service = (UserService) serviceFactory.<User>getService(UserServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path;

        String login = request.getParameter(FieldConstant.FIELD_NAME_LOGIN);
        String password = request.getParameter(FieldConstant.FIELD_NAME_PASSWORD);
        String confirmPassword = request.getParameter(FieldConstant.FIELD_NAME_CONFIRM_PASSWORD);
        try {
            log.trace("Validating entered field...");
            Validator.validate(login, Validation.LOGIN);
            Validator.validate(password, Validation.PASSWORD);
            Validator.validate(confirmPassword, Validation.PASSWORD);

            if(password.equals(confirmPassword)) {
                log.trace("Inserting new user to database...");
                User user = new User();
                user.setUsername(login);
                user.setPassword(password);
                service.insert(user);

                log.trace("Redirect to login page..");
                path = "redirect:/login";
            } else {
                throw new PasswordEqualityException("Password and Confirm Password are not equal.");
            }
        } catch (InsertEntityException e) {
            log.info("Insert was failed. " + "Entered login: " + login + "; password: "
                    + password + "; confirm password: " + confirmPassword);
            Form registerForm = formBuilder.getRegistrationForm();
            registerForm.addError(languageManager.getString("message.error.user.exist"));

            log.trace("Redirect to register page with error message..");
            request.getSession().setAttribute("form", registerForm);
            path = "redirect:/register";
        } catch (PasswordEqualityException e) {
            log.info("Entered password and confirm password are not equal. " +
                    "Password: " + password + "; confirm password: " + confirmPassword);
            Form registerForm = formBuilder.getRegistrationForm();
            registerForm.addError(languageManager.getString("message.error.passwords.not.equal"));

            log.trace("Redirect to register page with error message..");
            request.getSession().setAttribute("form", registerForm);
            path = "redirect:/register";
        } catch (ValidateException e) {
            log.info("Validating failed. " +
                    "Entered login: " + login + "; password: "
                    + password + "; confirm password: " + confirmPassword);
            Form registerForm = formBuilder.getRegistrationForm();
            registerForm.addError(languageManager.getString("message.error.invalid.enter"));

            log.trace("Redirect to register page with error message..");
            request.getSession().setAttribute("form", registerForm);
            path = "redirect:/register";
        }

        return path;
    }

}
