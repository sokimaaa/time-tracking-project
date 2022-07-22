package timekeeping.my.controller.command.impl.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.controller.features.mail.ActivityEvent;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.Status;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.UpdateEntityException;
import timekeeping.my.service.impl.ActivityServiceImpl;
import timekeeping.my.service.ActivityService;

import javax.servlet.http.HttpServletRequest;

public class ChangeStatusCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(ChangeStatusCommand.class);
    private final ActivityService activityService;
    private final ActivityEvent activityEvent;

    public ChangeStatusCommand() {
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
        activityEvent = new ActivityEvent();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        try {
            Activity activity = activityService.get(activityId);
            activity.setStatus(setStatus(status));

            activityService.update(activity);
            activityEvent.notifySubscribers();
            log.trace("Activity was updated. Redirect to admin page..");
        } catch (UpdateEntityException e) {
            log.error("Activity was not updated. Redirect to admin page..");
        } catch (EntityNotFoundException e) {
            log.error("Activity is not found. Such activity does not exist. Activity id ==> " + activityId);
            log.trace("Redirect to admin page..");
        }

        return "redirect:/admin";
    }

    private Status setStatus(String status) {
        switch (status.toLowerCase()) {
            case "private":
                log.trace("Change status to Public.");
                return Status.PUBLIC;
            case "public":
                log.trace("Change status to Private.");
                return Status.PRIVATE;
            default:
                log.error("Provided wrong status ==> " + status);
                throw new IllegalArgumentException("Provided wrong status ==> " + status);
        }
    }

}

