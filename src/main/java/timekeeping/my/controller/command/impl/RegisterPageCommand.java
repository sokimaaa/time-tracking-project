package timekeeping.my.controller.command.impl;

import timekeeping.my.view.form.ConstantForm;
import timekeeping.my.view.form.Form;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * command to forward to the register page
 * sets 'register form'
 * */
public class RegisterPageCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Form registerForm = (Form) request.getSession().getAttribute("form");
        if(Objects.isNull(registerForm) || !registerForm.getId().equals(ConstantForm.FORM_REGISTER_ID))
            registerForm = formBuilder.getRegistrationForm();

        request.getSession().setAttribute("form", registerForm);
        return "/register.jsp";
    }

}
