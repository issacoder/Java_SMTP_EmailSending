package com.issacode;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //Email ID of Receiver need to be mentioned
        String receiver = "Receiver_Email";

        //Email ID of Sender need to be mentioned
        String sender = "Sender_Email";

        //Sending email using gmail smtp
        String host = "smtp.gmail.com";

        //System properties
        Properties properties = System.getProperties();

        //Setting up server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Get the Session object
        Session session = Session.getInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("YourEmail@gmail.com", "YourPassword");
            }
        });


        session.setDebug(true);
        try {
            //Default MimeMessage
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("This is Subject field");
            message.setText("Your message");

            System.out.println("Sending");

            //Sending email
            Transport.send(message);
            System.out.println("Succesfully sent");

        }
        catch (MessagingException mex){
            mex.printStackTrace();
        }

    }
}
