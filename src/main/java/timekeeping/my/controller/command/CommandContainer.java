package timekeeping.my.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.util.Parser;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static final Logger log = LoggerFactory.getLogger(CommandContainer.class);
    private static final Map<String, CommandEnum> commands;

    static {
        commands = new HashMap<>();

        // GET QUERIES
        commands.put("/login", CommandEnum.LOGIN_PAGE);
        commands.put("/register", CommandEnum.REGISTER_PAGE);
        commands.put("/logout", CommandEnum.LOGOUT);
        commands.put("/home", CommandEnum.HOME_PAGE);
        commands.put("/error", CommandEnum.ERROR);
        commands.put("/deny", CommandEnum.ACCESS_DENY);
        commands.put("/cabinet", CommandEnum.PERSONAL_CABINET_PAGE);
        commands.put("/create", CommandEnum.CREATE_ACTIVITY_PAGE);
        commands.put("/activity", CommandEnum.ACTIVITY_PAGE);
        commands.put("/admin", CommandEnum.ADMIN_PAGE);
        commands.put("/ban", CommandEnum.BAN_PAGE);
        commands.put("/top", CommandEnum.TOP_USERS);

        // POST QUERIES
        commands.put("/toLogin", CommandEnum.LOGIN);
        commands.put("/toRegister", CommandEnum.REGISTER);
        commands.put("/update", CommandEnum.UPDATE_USER);
        commands.put("/toCreate", CommandEnum.CREATE_ACTIVITY);
        commands.put("/join", CommandEnum.SEND_REQUEST_TO_JOIN);
        commands.put("/calculate", CommandEnum.CALCULATE_TIME);
        commands.put("/lock", CommandEnum.LOCK_USER);
        commands.put("/add", CommandEnum.CREATE_CATEGORY);
        commands.put("/change", CommandEnum.CHANGE_STATUS);
        commands.put("/accept", CommandEnum.ACCEPT_REQUEST);
        commands.put("/subscribe", CommandEnum.SUBSCRIBE);
        commands.put("/remove", CommandEnum.REMOVE_ACTIVITY);

        log.trace("Command container was successfully initialized");
    }

    private CommandContainer() {

    }

    /**
     * gets command from the command container
     * if any command was found return error-command
     * @param path the command path
     * @return command the command
     * */
    public static Command getCommand(String path) {
        path = Parser.parseCommand(path);
        if(path == null || !commands.containsKey(path))
            return commands.get("/error").getCommand();
        return commands.get(path).getCommand();
    }
}
