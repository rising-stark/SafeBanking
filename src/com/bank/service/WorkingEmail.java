package com.bank.service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
public class WorkingEmail {

    public static void sendEmail(String emailid,String subject,String message){
        try{
            String host ="smtp.gmail.com";
            String user = "codingfever43";
            String pass = "vacation1043";
            String to = emailid;
            String from = "codingfever43@gmail.com";
            //String messageText = "Plz go to the following URL to verify your registered account. http://localhost:8080/Safe_Banking@TRISHA/verify";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(message);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message sent successfully");
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public static void main(String [] args) {
    	for(int i=0;i<100;i++) {
    		sendEmail("president.sc@iitrpr.ac.in",""+i,"Hi!");
    	}
    }
}