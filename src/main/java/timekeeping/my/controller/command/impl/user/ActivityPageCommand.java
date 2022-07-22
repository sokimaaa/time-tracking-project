package timekeeping.my.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.impl.ActivityServiceImpl;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.impl.ActionServiceImpl;
import timekeeping.my.view.field.FieldConstant;
import timekeeping.my.view.form.ConstantForm;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;

/**
 * command to forward to the activity page
 * */
public class ActivityPageCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(ActivityPageCommand.class);
    private final ActivityService activityService;
    private final ActionService actionService;

    public ActivityPageCommand() {
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
        actionService = (ActionService) serviceFactory.<Action>getService(ActionServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");

        Activity activity;
        try {
            activity = activityService.get(activityId);
            log.trace("Activity is obtained...");
            request.setAttribute("activity", activity);
        } catch (EntityNotFoundException e) {
            log.error("Activity is not found by id ==> " + activityId);
            log.trace("Forwarding to error page..");
            return "/error.jsp";
        }

        Action action;
        try {
            action = actionService.getAction(user, activity);
            log.info("Action is obtained. Action: " + action);
        } catch (EntityNotFoundException e) {
            log.trace("Action is not obtained. Such action still does not exist.");
            action = null;
        }

        Form form = setForm(Objects.isNull(action), request);
        request.setAttribute("action", action);
        request.setAttribute("form", form);
        log.trace("Forwarding to activity profile page..");
        return "/activity_profile.jsp";
    }

    private Form setForm(boolean hasAction, HttpServletRequest request) {
        Form form = (Form) request.getSession().getAttribute("form");
        if(hasAction) {
            if(Objects.isNull(form) || !form.getId().equals(ConstantForm.FORM_JOIN_ID))
                form = formBuilder.getSendRequestForm();
        } else {
            if(Objects.isNull(form) || !form.getId().equals(ConstantForm.FORM_CALCULATE_ID))
                form = formBuilder.getCalculateForm();
        }
        return form;
    }

}
