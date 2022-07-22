package timekeeping.my.controller.features.mail;

import timekeeping.my.util.PropertiesUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public interface Subscriber {

    /**
     * sends email depends on event
     * @param event the enum event
     * */
    void handleEvent(Event event);

    /**
     * sends mail with some parameters
     * @param email the receiver's email
     * @param subject the subject of email
     * @param text the main part of email
     * */
    default void sendMail(String email, String subject, String text) throws MessagingException {
        final Properties properties = PropertiesUtil.getProperties();
        String senderMail = PropertiesUtil.getProperty("mail.smtps.user");

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage mimeMessage = new MimeMessage(mailSession);
        mimeMessage.setFrom(new InternetAddress(senderMail));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(text);

        Transport tr = mailSession.getTransport();
        tr.connect(null, PropertiesUtil.getProperty("mail.smtps.password"));
        tr.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        tr.close();
    }
}
