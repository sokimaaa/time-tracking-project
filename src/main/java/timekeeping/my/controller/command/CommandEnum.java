package timekeeping.my.controller.command;

import timekeeping.my.controller.command.impl.*;
import timekeeping.my.controller.command.impl.admin.*;
import timekeeping.my.controller.command.impl.user.*;

public enum CommandEnum {
    HOME_PAGE(new HomePageCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    LOGIN(new LoginCommand()),
    REGISTER_PAGE(new RegisterPageCommand()),
    REGISTER(new RegisterCommand()),
    LOGOUT(new LogoutCommand()),
    ERROR(new ErrorCommand()),
    ACCESS_DENY(new AccessDeniedPageCommand()),
    PERSONAL_CABINET_PAGE(new PersonalCabinetPageCommand()),
    UPDATE_USER(new UpdateUserCommand()),
    CREATE_ACTIVITY_PAGE(new InsertActivityPageCommand()),
    CREATE_ACTIVITY(new InsertActivityCommand()),
    SEND_REQUEST_TO_JOIN(new SendJoinRequestCommand()),
    ACTIVITY_PAGE(new ActivityPageCommand()),
    CALCULATE_TIME(new CalculateTimeCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    LOCK_USER(new LockCommand()),
    BAN_PAGE(new BanPageCommand()),
    CREATE_CATEGORY(new InsertCategoryCommand()),
    CHANGE_STATUS(new ChangeStatusCommand()),
    ACCEPT_REQUEST(new AcceptRequestCommand()),
    SUBSCRIBE(new SubscribeCommand()),
    TOP_USERS(new Top10UsersCommand()),
    REMOVE_ACTIVITY(new RemoveCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
