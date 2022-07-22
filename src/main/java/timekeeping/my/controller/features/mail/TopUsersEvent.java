package timekeeping.my.controller.features.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.ServiceFactory;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.impl.ActionServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class TopUsersEvent implements EventManager {

    private static final Logger log = LoggerFactory.getLogger(TopUsersEvent.class);
    private static final List<Subscriber> subscribers = new ArrayList<>();
    private static List<Action> topList;

    static {
        try {
            topList = ((ActionService) ServiceFactory.getInstance().<Action>getService(ActionServiceImpl.class)).getTopList();
        } catch (EntityNotFoundException e) {
            log.error("Fail to obtain top user's action. Event manager does not work.");
            throw new RuntimeException(e);
        }
    }

    /**
     * when someone updated spent time checked difference in the top list
     * if something changes notified all subscribers
     * */
    public void checkTopList() {
        try {
            List<Action> currentTopList = ((ActionService) ServiceFactory.getInstance().<Action>getService(ActionServiceImpl.class)).getTopList();
            if(!topList.equals(currentTopList)) {
                topList = currentTopList;
                notifySubscribers();
            }
        } catch (EntityNotFoundException e) {
            log.error("Fail to obtain top user's action. Event manager does not notify users.");
        }
    }

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return subscribers.contains(subscriber);
    }

    @Override
    public Subscriber addSubscriber(Subscriber subscriber) {
        log.trace("New subscriber in the Top Users Event.");
        subscribers.add(subscriber);
        return subscriber;
    }

    @Override
    public Subscriber removeSubscriber(Subscriber subscriber) {
        log.trace("Subscriber has left from the Top Users Event.");
        return subscriber;
    }

    @Override
    public void notifySubscribers() {
        new Thread(() -> {
            log.info("Start new Thread... Thread name: " + Thread.currentThread().getName());
            log.info("Current subscribers ==> " + subscribers);
            for (Subscriber subscriber: subscribers) {
                subscriber.handleEvent(Event.UPDATE_TOP_10_USERS);
            }
            log.trace("Some changes in the Top Users Event. Subscribers have been notified.");
        }).start();
    }

}
