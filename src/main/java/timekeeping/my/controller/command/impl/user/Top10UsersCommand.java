package timekeeping.my.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.controller.features.mail.TopUsersEvent;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.impl.ActionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * command to forward to the top user list page
 * */
public class Top10UsersCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(Top10UsersCommand.class);
    private final ActionService actionService;

    public Top10UsersCommand() {
        actionService = (ActionService) serviceFactory.<Action>getService(ActionServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            List<Action> topList = actionService.getTopList();
            request.setAttribute("topList", topList);

            log.trace("Forwarding to the top users page..");
            return "/top10users.jsp";
        } catch (EntityNotFoundException e) {
            log.error("Fail to obtain top user's action.");
            log.trace("Forwarding to the error page..");
            return "/error.jsp";
        }
    }

}
