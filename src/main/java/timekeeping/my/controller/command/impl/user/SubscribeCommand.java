package timekeeping.my.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.controller.features.mail.*;
import timekeeping.my.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * command to subscribe user on the event
 * after execution redirect to the home page
 * */
public class SubscribeCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(SubscribeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String event = request.getParameter("event");
        Subscriber subscriber = new Customer(user);

        EventManager eventManager = chooseEvent(event);
        updateEvent(eventManager, subscriber);

        return "redirect:/home";
    }

    /**
     * sets event manager by string-key
     * @param event the string-key of event
     * @return EventManager the event manager
     * */
    private EventManager chooseEvent(String event) {
        if(Objects.isNull(event)) {
            log.trace("Added new subscriber to the Activity Event.");
            return new ActivityEvent();
        } else {
            log.trace("Added new subscriber to the Top Users Event.");
            return new TopUsersEvent();
        }
    }

    /**
     * updates event using event manager
     * if subscriber already subscribed - unsubscribe
     * if subscriber is not subscribed - subscribe
     * @param eventManager the event manager
     * @param subscriber the subscriber
     * */
    private void updateEvent(EventManager eventManager, Subscriber subscriber) {
        if(eventManager.hasSubscriber(subscriber)) {
            eventManager.removeSubscriber(subscriber);
            log.trace("Subscriber was removed. Redirecting to home page..");
        } else {
            eventManager.addSubscriber(subscriber);
            log.trace("Subscriber was added. Redirecting to home page..");
        }
    }

}
