package timekeeping.my.controller.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.UserService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.WrongPasswordException;
import timekeeping.my.service.impl.UserServiceImpl;
import timekeeping.my.view.field.FieldConstant;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;

/**
 * command to redirect
 * 1) to the home page if all data entered successfully and such user exist
 * 2) to the login page if entered data invalid or such user does not exist
 * sets error messages if needed
 * */
public class LoginCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(LoginCommand.class);
    private final UserService userService;

    public LoginCommand() {
        userService = (UserService) serviceFactory.<User>getService(UserServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String path;

        String login = request.getParameter(FieldConstant.FIELD_NAME_LOGIN);
        String password = request.getParameter(FieldConstant.FIELD_NAME_PASSWORD);
        try {
            log.trace("Validating entered fields..");
            Validator.validate(login, Validation.LOGIN);
            Validator.validate(password, Validation.PASSWORD);

            log.trace("Getting user from database...");
            User user = userService.getUser(login, password);

            log.info(user.toString());
            log.trace("Checking user access...");
            if(user.getAccess().isAccess()) {
                request.getSession().setAttribute("role", user.getRole());
                request.getSession().setAttribute("user", user);

                log.trace("Access allowed. Redirect to home page...");
                path = "redirect:/home";
            } else {
                log.trace("Access denied. Redirect to ban page...");
                path = "redirect:/ban";
            }
        } catch (WrongPasswordException e) {
            log.info("Password does not suit. Entered login: " + login + "; password: " + password);
            Form loginForm = formBuilder.getLoginForm();
            loginForm.addError(languageManager.getString("message.error.password.wrong"));

            log.trace("Such user was not found. Redirect to login page with error..");
            request.getSession().setAttribute("form", loginForm);
            path = "redirect:/login";
        } catch (EntityNotFoundException e) {
            log.info("User was not found. Entered login: " + login + "; password: " + password + ".");
            Form loginForm = formBuilder.getLoginForm();
            loginForm.addError(languageManager.getString("message.error.user.not.exist"));

            log.trace("Such user was not found. Redirect to login page with error..");
            request.getSession().setAttribute("form", loginForm);
            path = "redirect:/login";
        } catch (ValidateException e) {
            log.info("Validating failed. Entered login: " + login + "; password: " + password + '.');
            Form loginForm = formBuilder.getLoginForm();
            loginForm.addError(languageManager.getString("message.error.invalid.password.login"));

            log.trace("Validation was not passed.. Redirect to login page with error..");
            request.getSession().setAttribute("form", loginForm);
            path = "redirect:/login";
        }
        return path;
    }

}
