package timekeeping.my.controller.command.impl.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.exception.RemoveEntityException;
import timekeeping.my.service.impl.ActivityServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * command to remove activity
 * after execution redirect to the admin page
 * */
public class RemoveCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(RemoveCommand.class);
    private final ActivityService activityService;

    public RemoveCommand() {
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String paramId = request.getParameter("id");
        int id = Integer.parseInt(paramId);
        try {
            log.info("Removing activity by id ==> " + id);
            activityService.remove(id);
        } catch (RemoveEntityException e) {
            log.error("Fail to remove. Perhaps entered non-existent activity's id ==> " + id);
        }

        log.trace("Redirect to admin page...");
        return "redirect:/admin";
    }

}
