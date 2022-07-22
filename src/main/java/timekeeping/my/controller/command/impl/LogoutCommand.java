package timekeeping.my.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * command to redirect to the login page
 * and clean session's attributes
 * */
public class LogoutCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        Iterator<String> sessionAttributes = request.getSession().getAttributeNames().asIterator();
        while (sessionAttributes.hasNext()) {
            request.getSession().removeAttribute(sessionAttributes.next());
        }

        return "redirect:/login";
    }

}
