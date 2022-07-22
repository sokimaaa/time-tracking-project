package timekeeping.my.controller;

import timekeeping.my.controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.CommandContainer;
import timekeeping.my.view.form.Form;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The Front Controller
 * */
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private static Controller instance;

    private Controller() {

    }

    public static synchronized Controller getInstance() {
        if(Objects.isNull(instance))
            instance = new Controller();
        return instance;
    }

    /**
     * processes request and response received from servlet
     * finds command by path and gains forward(redirect) string
     * then forward\redirect to next page
     * */
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Request processing...");
        String path = request.getServletPath();
        Command command = getCommand(path);

        String forward = execute(command, request);

        forward(forward, request, response);
        redirect(forward, response);
    }

    /**
     * executes command
     * if happens something unexpected forward to error page
     * @return string the forward path
     * */
    private String execute(Command command, HttpServletRequest request) {
        try {
            log.trace("Command execution get started..");
            return command.execute(request);
        } catch (Exception e) {
            log.error("Command was failed during execution ==> " + command);
            return "/error.jsp";
        }
    }

    /**
     * gets command from command container
     * */
    private Command getCommand(String path) {
        return CommandContainer.getCommand(path);
    }

    /**
     * redirects if prefix matches 'redirect:'
     * */
    private void redirect(String forward, HttpServletResponse response) throws IOException {
        if(forward.startsWith("redirect:")) {
            String redirect = forward.substring("redirect:".length() + 1);
            log.info("Redirecting to ==> " + redirect);
            response.sendRedirect(redirect);
        }
    }

    /**
     * forwards if prefix does not match 'redirect:'
     * */
    private void forward(String forward, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(!forward.startsWith("redirect:")) {
            log.info("Forwarding to ==> " + forward);
            request.getRequestDispatcher("/WEB-INF/jsp" + forward).forward(request, response);
        }
    }

}
