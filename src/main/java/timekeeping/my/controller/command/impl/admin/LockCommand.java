package timekeeping.my.controller.command.impl.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.exception.WrongBanStatusException;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.model.entity.Access;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.UserService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.UpdateEntityException;
import timekeeping.my.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * command to ban or unban user
 * after execution redirect to the admin page
 * */
public class LockCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(LockCommand.class);
    private final UserService userService;

    public LockCommand() {
        userService = (UserService) serviceFactory.<User>getService(UserServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String status = request.getParameter("ban");
        int userId = Integer.parseInt(request.getParameter("id"));

        String path;
        try {
            User user = userService.get(userId);

            log.trace("Retrieving ban status...");
            BanStatus banStatus = BanStatus.getBanStatus(status);
            processLock(banStatus, user);
            log.trace("Redirect to admin page..");
            path = "redirect:/admin";
        } catch (WrongBanStatusException e) {
            log.trace("Provided wrong ban status...");
            log.trace("Redirect to error page..");
            path = "redirect:/error";
        } catch (UpdateEntityException e) {
            log.error("Fail to update user.. ");
            log.trace("Redirect to error page..");
            path = "redirect:/error";
        } catch (EntityNotFoundException e) {
            log.error("Such user does not exist. Provided wrong user id ==> " + userId);
            log.trace("Redirect to error page..");
            path = "redirect:/error";
        }

        return path;
    }

    /**
     * @param banStatus the ban status
     * @param user the user to ban or unban
     * @throws WrongBanStatusException if provided wrong ban status
     * @throws UpdateEntityException fail to update user
     * */
    private void processLock(BanStatus banStatus, User user) throws WrongBanStatusException, UpdateEntityException {
        switch (banStatus) {
            case BANNED:
                log.info("To Unlocking account ==> " + user);
                user.setAccess(Access.ALLOW);
                userService.update(user);
                break;
            case UNBANNED:
                log.info("To Locking account ==> " + user);
                user.setAccess(Access.DENY);
                userService.update(user);
                break;
        }
    }

    private enum BanStatus {
        BANNED("true"), UNBANNED("false");
        private final String hasBan;

        BanStatus(String hasBan) {
            this.hasBan = hasBan;
        }

        static BanStatus getBanStatus(String status) throws WrongBanStatusException {
            for (BanStatus banStatus: BanStatus.values()) {
                if(banStatus.hasBan.equals(status)) {
                    return banStatus;
                }
            }
            throw new WrongBanStatusException("Provided wrong ban status ==> " + status);
        }
    }

}
