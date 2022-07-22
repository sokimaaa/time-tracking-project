package timekeeping.my.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

/**
 * command to forward to the access denied page
 * */
public class AccessDeniedPageCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "/access_denied.jsp";
    }

}
