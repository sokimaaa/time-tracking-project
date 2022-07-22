package timekeeping.my.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

/**
 * command to forward to the ban page
 * */
public class BanPageCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return "/ban.jsp";
    }

}