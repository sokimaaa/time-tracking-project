package timekeeping.my.controller.features.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.entity.User;

import javax.mail.MessagingException;
import java.util.Objects;

public class Customer implements Subscriber {

    private static final Logger log = LoggerFactory.getLogger(Customer.class);
    private final User user;

    public Customer(User user) {
        this.user = user;
    }

    @Override
    public void handleEvent(Event event) {
        String email = user.getEmail();
        StringBuilder subjectBuilder = new StringBuilder();
        StringBuilder textBuilder = new StringBuilder();

        subjectBuilder.append("Something changed in the ").append(event.getName());
        textBuilder.append("Dear, ").append(user.getUsername())
                .append(". You have subscribed to our event: ").append(event.getName()).append('.').append('\n')
                .append("We think, you would like to know about changes in the event.").append('\n').append('\n')
                .append("If you do not want to receive such messages you could turn off this feature into your account profile.");

        try {
            sendMail(email, subjectBuilder.toString(), textBuilder.toString());
            log.info("Email was send to address ==> " + email);
        } catch (MessagingException e) {
            log.error("Fail to send email. Perhaps enter wrong email ==> " + user.getEmail());
            log.trace("Email was not send");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(user, customer.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
