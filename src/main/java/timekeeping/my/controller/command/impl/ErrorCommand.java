package timekeeping.my.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

/**
 * command to forward to the error page
 * */
public class ErrorCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "/error.jsp";
    }

}
