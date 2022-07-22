package timekeeping.my.controller.command.impl.user;

import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.model.entity.User;
import timekeeping.my.view.field.FieldConstant;
import timekeeping.my.view.form.ConstantForm;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * command to forward to the personal cabinet page
 * sets 'cabinet form'
 * */
public class PersonalCabinetPageCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Form cabinetForm = (Form) request.getSession().getAttribute("form");
        if(Objects.isNull(cabinetForm) || !cabinetForm.getId().equals(ConstantForm.FORM_CABINET_ID))
            cabinetForm = formBuilder.getPersonalCabinetForm();

        request.setAttribute("form", cabinetForm);
        return "/cabinet.jsp";
    }

}
