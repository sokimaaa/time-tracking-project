package timekeeping.my.controller.command.impl.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.model.entity.Access;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.User;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.service.UserService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.UpdateEntityException;
import timekeeping.my.service.impl.ActivityServiceImpl;
import timekeeping.my.service.impl.UserServiceImpl;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.impl.ActionServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * command to accept request
 * after execution redirect to the admin page
 * */
public class AcceptRequestCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(AcceptRequestCommand.class);
    private final ActionService actionService;
    private final UserService userService;
    private final ActivityService activityService;

    public AcceptRequestCommand() {
        userService = (UserService) serviceFactory.<User>getService(UserServiceImpl.class);
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
        actionService = (ActionService) serviceFactory.<Action>getService(ActionServiceImpl.class);

    }

    @Override
    public String execute(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("user"));
        int activityId = Integer.parseInt(request.getParameter("activity"));

        User user;
        try {
            user = userService.get(userId);
            log.trace("User is obtained..");
        } catch (EntityNotFoundException e) {
            log.info("Such user does not exist. User id: " + userId);
            log.trace("Forwarding to error page..");
            return "/error.jsp";
        }

        Activity activity;
        try {
            activity = activityService.get(activityId);
            log.trace("Activity is obtained...");
        } catch (EntityNotFoundException e) {
            log.info("Such activity does not exist. User id: " + activityId);
            log.trace("Forwarding to error page..");
            return "/error.jsp";
        }

        Action action;
        try {
             action = actionService.getAction(user, activity);
        } catch (EntityNotFoundException e) {
            log.info("Such action does not exist. User: " + user.getId() + "; Activity: " + activityId);
            log.trace("Forwarding to error page..");
            return "/error.jsp";
        }

        action.setAccess(Access.ALLOW);
        activity.incrementCount();

        try {
            activityService.update(activity);
            actionService.updateAction(action);
        } catch (UpdateEntityException e) {
            log.trace("Fail to update access.");
            log.trace("Forwarding to error page..");
            return "/error.jsp";
        }

        log.trace("Redirect to admin page..");
        return "redirect:/admin";
    }

}
