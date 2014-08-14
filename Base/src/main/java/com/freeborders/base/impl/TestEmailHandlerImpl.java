package com.freeborders.base.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.freeborders.base.api.TestEmailHandler;
import com.freeborders.base.log.Logger;

/**
 *
 * @author nelson
 */
public final class TestEmailHandlerImpl implements TestEmailHandler {

    private Properties properties;

    public TestEmailHandlerImpl(Properties properties) {
        this.properties = properties;
        this.properties.put("mail.smtp.host", this.properties.get("host"));
        this.properties.put("mail.smtp.port", this.properties.get("port"));
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.socketFactory.port", "465");
        this.properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    @Override
    public void sendEmails(final String emailContent) {
        final String mailFrom = this.properties.getProperty("mailFrom");
        final String password = this.properties.getProperty("password");
        final String mailTo = this.properties.getProperty("mailTo");
        final Session session = Session.getDefaultInstance(this.properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        });
        if (mailTo != null && mailTo.length() > 0) {
            String[] mailTos = mailTo.split(",");
            for (String tempMailTo : mailTos) {
                this.sendEmail(session, mailFrom, tempMailTo, emailContent);
            }
        } else {
            Logger.info("sendEmail", "no sender emails");
        }
    }

    private void sendEmail(final Session session, final String mailFrom, final String mailTo, final String emailContent) {
        try {
            Message message = new MimeMessage(session);
            Address from = new InternetAddress(mailFrom);
            message.setFrom(from);
            Address to = new InternetAddress(mailTo);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject( new Date() + " Test Report");
            //message.setText(emailContent);
            message.setSentDate(new Date());
            //设置html
            Multipart multipart = new MimeMultipart("mixed");
            MimeBodyPart contentPart = new MimeBodyPart();
            Multipart bodyMultipart = new MimeMultipart("related");
            contentPart.setContent(bodyMultipart);
            MimeBodyPart htmlPart = new MimeBodyPart();
            bodyMultipart.addBodyPart(htmlPart);
            htmlPart.setContent(emailContent, "text/html;charset=GBK");
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            Transport.send(message);
            Logger.debug("send message", "send message sucessfully");
        } catch (MessagingException ex) {
            Logger.severe("sendEmail", ex.getMessage(), ex);
        }
    }
}
