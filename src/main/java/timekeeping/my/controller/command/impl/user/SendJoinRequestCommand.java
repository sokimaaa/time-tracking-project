package timekeeping.my.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.model.entity.Access;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.InsertEntityException;
import timekeeping.my.service.impl.ActivityServiceImpl;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.impl.ActionServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * command to send join request
 * after execution redirect to the selected activity
 * */
public class SendJoinRequestCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(SendJoinRequestCommand.class);
    private final ActivityService activityService;
    private final ActionService actionService;

    public SendJoinRequestCommand() {
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
        actionService = (ActionService) serviceFactory.<Action>getService(ActionServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");
        try {
            Activity activity = activityService.get(activityId);

            Action action = new Action();
            action.setUser(user);
            action.setActivity(activity);
            action.setAccess(Access.DENY);

            log.trace("Inserting action...");
            actionService.insert(action);
        } catch (InsertEntityException e) {
            log.error("Fail to insert action. Entered user: " + user + "; activity: " + activityId + '.');
            log.trace("Redirect to error page..");
            return "redirect:/error";
        } catch (EntityNotFoundException e) {
            log.error("Fail to obtain activity. Such activity does not exist. Activity id ==> " + activityId);
            log.trace("Redirect to error page..");
            return "redirect:/error";
        }

        log.trace("Redirect to activity profile..");
        return "redirect:/activity?id=" + activityId;
    }

}
