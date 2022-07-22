package timekeeping.my.controller.features.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ActivityEvent implements EventManager {

    private static final Logger log = LoggerFactory.getLogger(ActivityEvent.class);
    private static final List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public boolean hasSubscriber(Subscriber subscriber) {
        return subscribers.contains(subscriber);
    }

    @Override
    public Subscriber addSubscriber(Subscriber subscriber) {
        log.trace("New subscriber in the Activity Event.");
        subscribers.add(subscriber);
        return subscriber;
    }

    @Override
    public Subscriber removeSubscriber(Subscriber subscriber) {
        log.trace("Subscriber has left from the Activity Event.");
        subscribers.remove(subscriber);
        return subscriber;
    }

    @Override
    public void notifySubscribers() {
        new Thread(() -> {
            log.info("Start new Thread... Thread name: " + Thread.currentThread().getName());
            log.info("Current subscribers ==> " + subscribers);
            for (Subscriber subscriber: subscribers) {
                subscriber.handleEvent(Event.UPDATE_ACTIVITY);
            }
            log.trace("Some changes in the Activity Event. Subscribers have been notified.");
        }).start();
    }

}
