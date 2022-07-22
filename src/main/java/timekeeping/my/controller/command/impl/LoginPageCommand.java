package timekeeping.my.controller.command.impl;

import timekeeping.my.view.form.ConstantForm;
import timekeeping.my.view.form.Form;
import timekeeping.my.view.form.FormBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * command to forward to the login page
 * sets 'login form'
 * */
public class LoginPageCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Form loginForm = (Form) request.getSession().getAttribute("form");
        if(Objects.isNull(loginForm) || !loginForm.getId().equals(ConstantForm.FORM_LOGIN_ID))
            loginForm = formBuilder.getLoginForm();

        request.getSession().setAttribute("form", loginForm);
        return "/login.jsp";
    }

}
