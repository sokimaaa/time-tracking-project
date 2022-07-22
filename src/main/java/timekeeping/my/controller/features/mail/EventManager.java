package timekeeping.my.controller.features.mail;

public interface EventManager {

    /**
     * adds subscriber which agreed to be notified
     * @param subscriber the subscriber
     * @return added subscriber
     * */
    Subscriber addSubscriber(Subscriber subscriber);

    /**
     * removes subscriber which refused to be notified
     * @param subscriber the subscriber
     * @return removed subscriber
     * */
    Subscriber removeSubscriber(Subscriber subscriber);

    /**
     * checks containing subscribers
     * @param subscriber the subscriber
     * @return true or false
     * */
    boolean hasSubscriber(Subscriber subscriber);

    /**
     * sends email to all subscribers
     * */
    void notifySubscribers();

}
