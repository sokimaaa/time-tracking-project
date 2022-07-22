package timekeeping.my.controller.command.impl.user;

import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.view.form.ConstantForm;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * command to forward to the add activity page
 * sets 'add activity form'
 * */
public class InsertActivityPageCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Form addActivityForm = (Form) request.getSession().getAttribute("form");
        if(Objects.isNull(addActivityForm) || !addActivityForm.getId().equals(ConstantForm.FORM_ADD_ACTIVITY_ID))
            addActivityForm = formBuilder.getAddActivityForm();

        request.setAttribute("form", addActivityForm);
        return "/add_activity.jsp";
    }

}
