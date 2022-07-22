package timekeeping.my.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.controller.features.mail.TopUsersEvent;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.UpdateEntityException;
import timekeeping.my.service.impl.ActionServiceImpl;
import timekeeping.my.service.impl.ActivityServiceImpl;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;

/**
 * command to calculate spent time
 * after all redirect to the same page
 * */
public class CalculateTimeCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(CalculateTimeCommand.class);
    private final ActivityService activityService;
    private final ActionService actionService;
    private final TopUsersEvent topUsersEvent;

    public CalculateTimeCommand() {
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
        actionService = (ActionService) serviceFactory.<Action>getService(ActionServiceImpl.class);
        topUsersEvent = new TopUsersEvent();
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        int activityId = Integer.parseInt(request.getParameter("id"));
        String spentTime = request.getParameter("spent");

        try {
            Activity activity = activityService.get(activityId);
            Action action = actionService.getAction(user, activity);

            log.trace("Validating entered fields..");
            Validator.validate(spentTime, Validation.ENTER_TIME);

            action.setSpentLastSession(Integer.parseInt(spentTime));
            action.updateTotal();

            actionService.updateAction(action);
            topUsersEvent.checkTopList();
            log.trace("Action was updated successfully..");
        } catch (UpdateEntityException e) {
            log.error("Action was not updated successfully. Perhaps entered wrong time: " + spentTime);
            Form calculateForm = formBuilder.getCalculateForm();
            calculateForm.addError(languageManager.getString("message.error.invalid.time.admin"));

            log.trace("Update action was not successful.. Redirect to the same page with error..");
            request.getSession().setAttribute("form", calculateForm);
        } catch (ValidateException e) {
            log.info("Validating failed. Entered time: " + spentTime);
            Form calculateForm = formBuilder.getCalculateForm();
            calculateForm.addError(languageManager.getString("message.error.invalid.time"));

            log.trace("Validation was not passed.. Redirect to the same page with error..");
            request.getSession().setAttribute("form", calculateForm);
        } catch (EntityNotFoundException e) {
            log.info("Fail to obtain activity or action. Entered wrong activity id: " + activityId + " or user id: " + user.getId());
            log.trace("Such page does not exist.");
            return "/error.jsp";
        }

        return "redirect:/activity?id=" + activityId;
    }

}
