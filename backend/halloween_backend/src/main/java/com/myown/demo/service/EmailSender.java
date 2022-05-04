
package com.myown.demo.service;

import com.myown.demo.model.User;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import java.io.FileInputStream;
import java.io.IOException;
@Component
public class EmailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);


    private String createMailBody(){
        StringBuilder body = new StringBuilder();
        body.append("<h1>Kedves Felhasználó!</h1>");
        body.append("<p>Sikeresen feliratkoztál hírlevelünkre!</p>");
        body.append("<p>Hamarosan megkeresünk weboldalunk legújabb funkcióinak bemutatásával!</p>");
        body.append("<p>Üdvözlettel,</p>");
        body.append("<p>Halloween csapat</p>");
        body.append("<img src='").append("https://www.history.com/.image/t_share/MTY3NDg4OTU3NjQxMTQwMDEz/halloween-gettyimages-172988453.jpg").append("' width=\"25%\" height=\"25%\">");
        return body.toString();
    }

    /*private void addAttachment(HtmlEmail email){
        try {
            FileInputStream inputStream = new FileInputStream("");
            ByteArrayDataSource dataSource = new ByteArrayDataSource(inputStream, "text/plain");
            DataHandler dataHandler = new DataHandler(dataSource);
            email.attach(dataSource, "attachment.txt", "description");
        } catch (EmailException ex){
            LOGGER.error("Something went wrong with adding attachments to email. Exception type: " + ex);
        } catch (IOException ex2){
            LOGGER.error("Something went wrong with finding searched attachment file. Exception type: " + ex2);
        }
    }*/

    private void sendEmail(String emailAddress, String emailBody, String subject){
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setSSLOnConnect(true);
        email.setAuthentication("halloweenproject01@gmail.com", "HWproject1");
        try {
            email.setFrom("halloweenproject01@gmail.com");
            email.addTo(emailAddress, emailAddress);
            email.setSubject(subject);
            email.setHtmlMsg(emailBody);
            email.send();
        } catch (Exception ex) {
            LOGGER.error("Something went wrong with sending email. Exception type: " + ex);
        }
    }

    public void createAndSendEmail(User user){
        String emailAddress = user.getEmail();
        String emailBody = createMailBody();
        String subject = "Új feliratkozás - Responsive Halloween";
        sendEmail(emailAddress, emailBody, subject);
    }
}
