package timekeeping.my.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.InsertEntityException;
import timekeeping.my.service.impl.ActivityServiceImpl;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.impl.ActionServiceImpl;
import timekeeping.my.model.entity.*;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;

/**
 * command to insert activity
 * after all redirect to the add activity page
 * */
public class InsertActivityCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(InsertActivityCommand.class);
    private final ActivityService activityService;
    private final ActionService actionService;

    public InsertActivityCommand() {
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
        actionService = (ActionService) serviceFactory.<Action>getService(ActionServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        Activity activity = new Activity();

        String name = request.getParameter("name");
        Category category = new Category(Integer.parseInt(request.getParameter("category")));
        String description = request.getParameter("description");
        City city = new City(Integer.parseInt(request.getParameter("city")));
        User user = (User) request.getSession().getAttribute("user");
        Status status = Status.PRIVATE;

        activity.setName(name);
        activity.setCategory(category);
        activity.setCity(city);
        activity.setDescriptions(description);
        activity.setOwner(user);
        activity.setStatus(status);
        activity.incrementCount();

        Action action = new Action();
        action.setUser(user);
        action.setAccess(Access.ALLOW);

        try {
            log.trace("Validating entered fields..");
            Validator.validate(name, Validation.ACTIVITY_NAME);
            Validator.validate(description, Validation.DESCRIPTION);

            activityService.insert(activity);
            log.info("Activity was inserted ==> " + activity);
            activity = activityService.getActivity(name);
            action.setActivity(activity);

            actionService.insert(action);
            log.info("Action was inserted ==> " + action);
        } catch (InsertEntityException e) {
            log.info("Insert was failed. Entered activity name: " + name + ".");
            Form addActivityForm = formBuilder.getAddActivityForm();
            addActivityForm.addError(languageManager.getString("message.error.activity.exist"));

            log.trace("Redirect to the add activity page with error..");
            request.getSession().setAttribute("form", addActivityForm);
        } catch (ValidateException e) {
            log.info("Validating failed. Entered activity name: " + name + ".");
            Form addActivityForm = formBuilder.getAddActivityForm();
            addActivityForm.addError(languageManager.getString("message.error.invalid.name.description"));

            log.trace("Validation was not passed.. Redirect to add activity page with error..");
            request.getSession().setAttribute("form", addActivityForm);
        } catch (EntityNotFoundException e) {
            log.error("Fail while inserting activity. Activity does not exist after inserting.");
            return "/error.jsp";
        }

        return "redirect:/create";
    }

}
