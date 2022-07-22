package timekeeping.my.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    /**
     * execution method for command
     * @param request the request
     * @return string the path to go when command is executed
     * */
    String execute(HttpServletRequest request);
}
